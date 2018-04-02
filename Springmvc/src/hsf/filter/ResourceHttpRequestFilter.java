package hsf.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.support.ServletContextResource;

/**
 * 处理静态资源的过滤器
 * 用于解决Spring MVC在处理静态资源（js，css等）的时候也会被拦截器过滤的问题
 * 
 * @author mengyang
 *
 */
public class ResourceHttpRequestFilter implements Filter {
	
	private final Logger logger = Logger.getLogger(getClass());

	public static final String METHOD_HEAD = "HEAD";
	
	public void destroy() {

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
		Resource resource = getResource(request);
		if (resource == null) {
			logger.debug("No matching resource found - returning 404");
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// check the resource's media type
		MediaType mediaType = getMediaType(request, resource);
		if (mediaType != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Determined media type [" + mediaType + "] for " + resource);
			}
		}
		else {
			if (logger.isDebugEnabled()) {
				logger.debug("No media type found for " + resource + " - returning 404");
			}
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// header phase
		if (new ServletWebRequest(request, response).checkNotModified(resource.lastModified())) {
			logger.debug("Resource not modified - returning 304");
			return;
		}
		setHeaders(response, resource, mediaType);

		// content phase
		if (METHOD_HEAD.equals(request.getMethod())) {
			logger.trace("HEAD request - skipping content");
			return;
		}
		writeContent(response, resource);

	}

	public void init(FilterConfig arg0) throws ServletException {

	}
	
	//获取请求的静态资源
	protected Resource getResource(HttpServletRequest request) throws UnsupportedEncodingException {
		
		String URI = URLDecoder.decode(request.getRequestURI(), "utf-8");
		String context = request.getContextPath();
		String path = URI.substring(URI.indexOf(context) + context.length());
		Resource resource = new ServletContextResource(request.getSession().getServletContext(), path);
		if (resource.exists() && resource.isReadable()) {
			if (logger.isDebugEnabled()) {
				logger.debug("Found matching resource: " + resource);
			}
			return resource;
		}
		else if (logger.isTraceEnabled()) {
			logger.trace("Relative resource doesn't exist or isn't readable: " + resource);
		}
		return null;
	}

	//获取资源的MediaType
	protected MediaType getMediaType(HttpServletRequest request, Resource resource) {
		String mimeType = request.getSession().getServletContext().getMimeType(resource.getFilename());
		return (StringUtils.hasText(mimeType) ? MediaType.parseMediaType(mimeType) : null);
	}

	//设置响应头部
	protected void setHeaders(HttpServletResponse response, Resource resource, MediaType mediaType) throws IOException {
		long length = resource.contentLength();
		if (length > Integer.MAX_VALUE) {
			throw new IOException("Resource content too long (beyond Integer.MAX_VALUE): " + resource);
		}
		response.setContentLength((int) length);
		response.setContentType(mediaType.toString());
	}

	//直接把资源流写到响应中
	protected void writeContent(HttpServletResponse response, Resource resource) throws IOException {
		
		FileCopyUtils.copy(resource.getInputStream(), response.getOutputStream());
	}

}
