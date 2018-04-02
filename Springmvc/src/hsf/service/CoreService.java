package hsf.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import hsf.weixin.popular.bean.message.EventMessage;
import hsf.weixin.popular.util.MessageUtil;

public class CoreService {
    /**
     * 处理微信发来的请求
     * @param request
     * @return xml
     */
    public static String processRequest(HttpServletRequest request) {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型！";
        try {
            // 调用parseXml方法解析请求消息
            Map<String,String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号
            String fromUserName = (String) requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = (String) requestMap.get("ToUserName");
            // 消息类型
            String msgType = (String) requestMap.get("MsgType");
            System.out.println("fromUserName:"+fromUserName+",toUserName="+toUserName+",msgType:"+msgType);
            // 回复消息
            EventMessage eMessage = new EventMessage();
            eMessage.setToUserName(toUserName);
            eMessage.setFromUserName(fromUserName);
            eMessage.setCreateTime((int) new Date().getTime());
            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "您发送的是文本消息！";
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "您发送的是小视频消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = (String) requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                	String eventKey = requestMap.get("EventKey");  
                	System.out.println("eventKey"+eventKey);
                	if("lianxiwomen".contentEquals(eventKey)){
                		StringBuilder sb = new StringBuilder();
                		sb.append("广东守创者智网科技有限公司\n");
                		sb.append("联系人：杨小姐\n");
                		sb.append("手机：13713124389\n");
                		sb.append("电话：0769-22827088 23181588\n");
                		sb.append("传真：0769-22827099\n");
                		sb.append("邮箱：xingda9508@163.com \n");
                		sb.append("地址：东莞市厚街镇石角路顺升科技园 \n");
                		sb.append("网址：http://www.sczzw888.com/ \n");
                		respContent = sb.toString();
                	}
                	eMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                }
            }
            // 设置消息的内容
            eMessage.setContent(respContent);
            // 将文本消息对象转换成xml
            respXml = MessageUtil.messageToXml(eMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }
}