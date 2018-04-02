package hsf.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;

import hsf.common.constant.Common;
import hsf.common.utils.RequestTool;
import hsf.service.CoreService;
import hsf.weixin.popular.bean.message.EventMessage;
import hsf.weixin.popular.bean.xmlmessage.XMLMessage;
import hsf.weixin.popular.bean.xmlmessage.XMLTextMessage;
import hsf.weixin.popular.support.ExpireKey;
import hsf.weixin.popular.support.expirekey.DefaultExpireKey;
import hsf.weixin.popular.util.SignatureUtil;
import hsf.weixin.popular.util.XMLConverUtil;

public class CoreServlet extends HttpServlet {

	/**
	 * 确认请求来自微信服务器 
	 */
	private static final long serialVersionUID = 4187646544017932939L;
	
	//重复通知过滤
    private static ExpireKey expireKey = new DefaultExpireKey();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 微信加密签名
			String signature = request.getParameter("signature");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
			// 随机字符串
			String echostr = request.getParameter("echostr");
			PrintWriter out = response.getWriter();
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
			if (SignatureUtil.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			}
			out.close();
			out = null;
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	/**
     * 处理微信服务器发来的消息
	 * @throws IOException 
     */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
        ServletOutputStream outputStream = response.getOutputStream();
		// 消息的接收、处理、响应
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //首次请求申请验证,返回echostr
        if(echostr!=null){
            outputStreamWrite(outputStream,echostr);
            return;
        }

        //验证请求签名
        if(!signature.equals(SignatureUtil.generateEventMessageSignature(Common.WX_TOKEN_STRING,timestamp,nonce))){
            System.out.println("The request signature is invalid");
            return;
        }
        if(inputStream!=null){
            //转换XML
            EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class,inputStream);
            String key = eventMessage.getFromUserName() + "__"
            				   + eventMessage.getToUserName() + "__"
            				   + eventMessage.getMsgId() + "__"
            				   + eventMessage.getCreateTime();
            if(expireKey.exists(key)){
            	//重复通知不作处理
            	return;
            }else{
            	expireKey.add(key);
            }

            //创建回复
            XMLMessage xmlTextMessage = new XMLTextMessage(
                    eventMessage.getFromUserName(),
                    eventMessage.getToUserName(),
                    "你好");
            //回复
            xmlTextMessage.outputStreamWrite(outputStream);
            return;
        }
        outputStreamWrite(outputStream,"");
	}

	/**
     * 数据流输出
     * @param outputStream
     * @param text
     * @return
     */
    private boolean outputStreamWrite(OutputStream outputStream,String text){
        try {
            outputStream.write(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
