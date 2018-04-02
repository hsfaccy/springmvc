package hsf.controller;

import java.util.List;

import hsf.model.User;
import hsf.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  
@RequestMapping("/user")  
public class UserController {  
    @Resource  
    private UserService userService;  
      
    @RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        User user = this.userService.getUserById(userId);  
        model.addAttribute("user", user);  
        return "showUser";  
    } 
    
    @RequestMapping("/list")  
    public String list(HttpServletRequest request,Model model){
        int id = Integer.parseInt(request.getParameter("id"));  
    	List<User> list = this.userService.getUserList(id);
    	model.addAttribute("list", list);
    	return "list";
    }
}  
