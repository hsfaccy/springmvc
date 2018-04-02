package hsf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {

	@RequestMapping(value="/hello")
	public String hello(){
		System.out.println("hello world!");
		return "hello";
	}
	
	@RequestMapping(value="/ok")
    @ResponseBody
    public Object ok(){
        System.out.println("ok");
        List<String> list=new ArrayList<String>(); 
        list.add("���ӻ�"); 
        list.add("����"); 
        list.add("ɽ��ʡ"); 
        list.add("�෢��"); 
        list.add("D���"); 
        list.add("�淶"); 
        list.add("������"); 
        list.add("������"); 
        list.add("���߶�"); 
        return list; 
    }
}
