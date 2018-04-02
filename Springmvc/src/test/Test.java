package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import hsf.weixin.popular.api.MenuAPI;
import hsf.weixin.popular.api.MessageAPI;
import hsf.weixin.popular.api.TokenAPI;
import hsf.weixin.popular.api.UserAPI;
import hsf.weixin.popular.bean.BaseResult;
import hsf.weixin.popular.bean.menu.Button;
import hsf.weixin.popular.bean.menu.MenuButtons;
import hsf.weixin.popular.bean.menu.selfmenu.CurrentSelfmenuInfo;
import hsf.weixin.popular.bean.message.ApiAddTemplateResult;
import hsf.weixin.popular.bean.message.GetAllPrivateTemplateResult;
import hsf.weixin.popular.bean.message.GetIndustryResult;
import hsf.weixin.popular.bean.message.MessageSendResult;
import hsf.weixin.popular.bean.message.massmessage.Filter;
import hsf.weixin.popular.bean.message.massmessage.MassTextMessage;
import hsf.weixin.popular.bean.message.message.Message;
import hsf.weixin.popular.bean.message.message.TextMessage;
import hsf.weixin.popular.bean.message.templatemessage.TemplateMessage;
import hsf.weixin.popular.bean.message.templatemessage.TemplateMessageItem;
import hsf.weixin.popular.bean.message.templatemessage.TemplateMessageResult;
import hsf.weixin.popular.bean.token.Token;
import hsf.weixin.popular.bean.user.Group;

public class Test {
	public static void main(String[] args) {
		//获取access_token
		Token token = TokenAPI.token("wx3c4aad80afd2105b","bf47ae653e0b76277f6714d129c0c0fe");
		System.out.println(token.getAccess_token());
		try {
			String access_token = "8_d6mF5j6Ibdy096FaEvW9MXI976Vz1fkXS2IqhmZ_drZq7yoqXsKdutm9BA7ZOj1XRcgctRJWdNXUCgLLigoM7yw4sVRK0R37vdKKqRoOtmbzMhzXs-IHWHTwxWNwcUZl4UHVP6jIE3-5FA3MDRGdADALZO";
			// 获取分组ID
//			Group group = UserAPI.groupsGet(access_token);
//			String gid = group.getGroups().get(0).getId();
//			//文本群发
//			MassTextMessage textMessage = new MassTextMessage("分组群发文本消息1");
//			//设置分组
//			textMessage.setFilter(new Filter(false,gid));
//			MessageAPI.messageMassSendall(access_token, textMessage);
			
			//根据openId 群发
			//文本消息发送根据openid
//			TextMessage textMessage = new TextMessage("oAFbO02Kds3wFq8XKxLigduxxYOM","hello world");
//			BaseResult result = new BaseResult();
//			result = MessageAPI.messageCustomSend(access_token,textMessage);
			//获取模版
//			TemplateMessageResult result = new TemplateMessageResult();
//			TemplateMessage template = new TemplateMessage();
//			LinkedHashMap<String, TemplateMessageItem> linkedHashMap = new LinkedHashMap<String, TemplateMessageItem>();
//			linkedHashMap.put("remark", new TemplateMessageItem("请注意，以免给你带来损失！","#173177"));
//			linkedHashMap.put("keyword5", new TemplateMessageItem("火警","#173177"));
//			linkedHashMap.put("keyword4", new TemplateMessageItem("3防区","#173177"));
//			linkedHashMap.put("keyword3", new TemplateMessageItem("2014年12月8日 14:28","#173177"));
//			linkedHashMap.put("keyword2", new TemplateMessageItem("001304C30001","#173177"));
//			linkedHashMap.put("keyword1", new TemplateMessageItem("办公室1号报警器","#173177"));
//			linkedHashMap.put("first", new TemplateMessageItem("你好，智能设备报告警情信息","#173177"));
//			template.setTouser("oAFbO02Kds3wFq8XKxLigduxxYOM");
//			template.setTemplate_id("p9LwInlb4DDsg6y13ekJlNCO2t1UkZqyFyeAvUY6vxU");
//			template.setData(linkedHashMap);
//			result = MessageAPI.messageTemplateSend(access_token, template);
//			System.out.println(result.toString());
//			CurrentSelfmenuInfo info = MenuAPI.get_current_selfmenu_info(access_token);
//			System.out.println(info);
//			Button sub = new Button();
//	        sub.setType("view");
//	        sub.setName("关于我们");
//	        sub.setUrl("http://www.xingdazg.com/");
//			Button sub1 = new Button();
//	        sub1.setType("view");
//	        sub1.setName("工程案例");
//	        sub1.setUrl("https://active.clewm.net/DjxqwR?qrurl=http%3A%2F%2Fqr14.cn%2FDjxqwR&gtype=1&uname=176****1578&key=6a84a15281c15ec0c1946103fdda1f8c0a05f89669");
//	        Button sub2 = new Button();
//	        sub2.setType("view");
//	        sub2.setName("资质证书");
//	        sub2.setUrl("https://active.clewm.net/CNrLYq?qrurl=http%3A%2F%2Fqr14.cn%2FCNrLYq&gtype=1&uname=176****1578&key=6447d1528b62c897f194624592dd3300d51aa02468");
//	        Button sub3 = new Button();
//	        sub3.setType("view");
//	        sub3.setName("公司动态");
//	        sub3.setUrl("https://active.clewm.net/EcsMzi?qrurl=http%3A%2F%2Fqr14.cn%2FEcsMzi&gtype=1&uname=176****1578&key=d233315c4ba75861419462d34aa6449dab349e7538");
//	        Button sub4 = new Button();
//	        sub4.setType("view");
//	        sub4.setName("行业新闻");
//	        sub4.setUrl("https://active.clewm.net/ChPg6A?qrurl=http%3A%2F%2Fqr14.cn%2FChPg6A&gtype=1&uname=176****1578&key=b54d215bf9dbac36e19462bfeef21427ef289a4660");
//	        Button sub5 = new Button();
//	        sub5.setType("click");
//	        sub5.setName("守创者");
//	        List<Button> subList = new ArrayList<Button>();
//	        subList.add(sub);
//	        subList.add(sub1);
//	        subList.add(sub2);
//	        subList.add(sub3);
//	        subList.add(sub4);
//	        sub5.setSub_button(subList);
//	        
//	        Button sub22 = new Button();
//	        subList = new ArrayList<Button>();
//	        subList.add(new Button("view","智能井盖","https://active.clewm.net/FU0h4O?qrurl=http%3A%2F%2Fqr14.cn%2FFU0h4O&gtype=1&uname=176****1578&key=113b11560d29d405019462b984a1c0934fd692e801"));
//	        subList.add(new Button("view","水箅","https://active.clewm.net/DXq0pB?qrurl=http%3A%2F%2Fqr14.cn%2FDXq0pB&gtype=1&uname=176****1578&key=6adbc15238f5532cf19462e4c1a7a5b999f1a7c892"));
//	        subList.add(new Button("view","沟盖","https://active.clewm.net/BViIMW?qrurl=http%3A%2F%2Fqr14.cn%2FBViIMW&gtype=1&uname=176****1578&key=9580a1533f45940e319462f45748b7c9c028e6e978"));
//	        sub22.setType("click");
//	        sub22.setName("产品中心");
//	        sub22.setSub_button(subList);
//	        
//	        Button sub33 = new Button();
//	        subList = new ArrayList<Button>();
//	        subList.add(new Button("view","在线留言","https://active.clewm.net/CHQl21?qrurl=http%3A%2F%2Fqr14.cn%2FCHQl21&gtype=1&uname=176****1578&key=f651215d81bdf10af1960623634872a23a5fcfa481"));
//	        subList.add(new Button("view","问题上报","http://110.88.128.46:58081/workflow/wechatupload/edit"));
//	        Button sub6 = new Button();
//	        sub6.setType("click");
//	        sub6.setName("联系我们");
//	        sub6.setKey("lianxiwomen");
//	        subList.add(sub6);
//	        sub33.setType("click");
//	        sub33.setName("服务中心");
//	        sub33.setSub_button(subList);
//	        
//	        
//	        MenuButtons btn1 = new MenuButtons();
//	        btn1.setButton(new Button[]{sub5,sub22,sub33});
//	        MenuAPI.menuCreate(access_token, btn1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 获取分组ID
//		Group group = UserAPI.groupsGet(token.getAccess_token());
//		System.out.println(group.getGroups());
	}
}
