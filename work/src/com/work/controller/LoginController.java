package com.work.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.work.entity.UserInfo;
import com.work.service.UserInfoService;

@Controller
public class LoginController {
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping("login")
	public @ResponseBody String login(String username,String password,HttpServletRequest request) throws IOException{
		UserInfo user = userInfoService.login(username,password);
		
		String msg = null;
		if(user==null){
			msg = "用户名或密码错误";
			return msg;
		}
		request.getSession().setAttribute("user", user);
		return msg;
	}
	@RequestMapping("logout")
	public String logout(HttpSession session) throws IOException{
		Enumeration<String> em = session.getAttributeNames();
        while (em.hasMoreElements()) {
        	session.removeAttribute(em.nextElement().toString());
        }
        session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("main/main")
	public String main(){
		return "/main/main";
	}
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
}
