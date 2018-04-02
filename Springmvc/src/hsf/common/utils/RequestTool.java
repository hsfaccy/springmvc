package hsf.common.utils;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;





public class RequestTool {


	/**
	 * 获取请求参数 存放到 map 里
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getParameterMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<?> enu=request.getParameterNames();  
		while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			map.put(paraName, request.getParameter(paraName)!=null?request.getParameter(paraName):"");
		}  
		return map;
	}
	
	
	/**
	 * 封装请求参数
	 * @param request
	 * @param classpaht
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static Object requestToBean(HttpServletRequest request,Class bean) throws UnsupportedEncodingException{

		Object o = null;
		try 
		{

			o = bean.newInstance();

        Field[] fieldlist = bean.getDeclaredFields();
        for(int i = 0;i<fieldlist.length;i++)
        {
        	Field newfield = fieldlist[i];
        	newfield.setAccessible(true);   
        	String value = request.getParameter(newfield.getName());	
        	if(value != null && !value.equals("null")&&!value.equals("")){	
        			value = java.net.URLDecoder.decode(value,"UTF-8");
        			
        			String valuetype = newfield.getType().getName();
        	
                    if(valuetype.equals("java.lang.Integer"))
                    {
                         newfield.set(o,Integer.valueOf(value));
                    }
                    
                    if(valuetype.equals("long")||valuetype.equals("java.lang.Long"))
                    {
                    
                         newfield.set(o,Long.valueOf(value));
                    }
                    if(valuetype.equals("java.lang.String"))
                    {
                         newfield.set(o,value);
                    }
                    //===================zcc==========================
                    
                    if(valuetype.equals("int"))
                    {
                         newfield.set(o,Integer.valueOf(value));
                    }
                    if(valuetype.equals("java.util.Date"))
                    {
                    	newfield.set(o,str2Date(value, "yyyy-MM-dd HH:mm:ss"));
                    }
                    if(valuetype.equals("java.math.BigDecimal"))
                    {
                    	newfield.set(o,new BigDecimal(value));
                    }
                    //=============================================
        	}
        }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
        return o;
	}
	
	public static Date str2Date(String str, String format){
		  if (null != str && !"".equals(str)) {
		    SimpleDateFormat sdf = new SimpleDateFormat(format);
		    Date date = null;
		    try {
		     date = sdf.parse(str);
		     return date;
		   } catch (ParseException e) {
		     e.printStackTrace();
		  }
		}
		return null;
	}
}
