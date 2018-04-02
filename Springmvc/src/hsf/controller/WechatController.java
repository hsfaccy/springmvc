package hsf.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hsf.common.constant.Common;
import hsf.service.CoreService;
import hsf.service.WechatService;
import hsf.weixin.popular.bean.message.EventMessage;
import hsf.weixin.popular.bean.xmlmessage.XMLMessage;
import hsf.weixin.popular.bean.xmlmessage.XMLTextMessage;
import hsf.weixin.popular.support.ExpireKey;
import hsf.weixin.popular.support.TokenManager;
import hsf.weixin.popular.support.expirekey.DefaultExpireKey;
import hsf.weixin.popular.util.SignatureUtil;
import hsf.weixin.popular.util.XMLConverUtil;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	@Resource
	private WechatService wechatService;

	//重复通知过滤
    private static ExpireKey expireKey = new DefaultExpireKey();
    
	@RequestMapping("/request")
	public void request(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
        System.out.println("1");
        //验证请求签名
        if(!signature.equals(SignatureUtil.generateEventMessageSignature(TokenManager.getToken(Common.WX_APPID),timestamp,nonce))){
            System.out.println("The request signature is invalid");
            return;
        }
        System.out.println("2");
        if(inputStream!=null){
            //转换XML
            EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class,inputStream);
            String key = eventMessage.getFromUserName() + "__"
            				   + eventMessage.getToUserName() + "__"
            				   + eventMessage.getMsgId() + "__"
            				   + eventMessage.getCreateTime();
            System.out.println(key);
            if(expireKey.exists(key)){
            	//重复通知不作处理
            	return;
            }else{
            	expireKey.add(key);
            }
            System.out.println("3");
            //创建回复
            XMLMessage xmlTextMessage = new XMLTextMessage(
                    eventMessage.getFromUserName(),
                    eventMessage.getToUserName(),
                    "你好");
            System.out.println("4");
            //回复
            xmlTextMessage.outputStreamWrite(outputStream);
            return;
        }
        outputStreamWrite(outputStream,"");
	}
	
	@RequestMapping("/view")  
	public String view(HttpServletRequest request,Model model){
	    System.out.println("11111111111111111");
		return "wechat/view";
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
