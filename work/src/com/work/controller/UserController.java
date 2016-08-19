package com.work.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.work.entity.UserInfo;
import com.work.service.UserInfoService;
import com.work.util.Pager;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserInfoService userInfoService;

//	@RequestMapping("/list")
//	public ModelAndView list(HttpServletRequest request){
//		
//		ModelAndView mnv = null;
//		if(((UserInfo)request.getSession().getAttribute("user")).getPermission()!=0){ //判断当前用户是否有权限
//			
//			List<UserInfo> lists = userInfoService.findAll();
//			mnv = new ModelAndView("user/userList");
//			if(lists!=null&&lists.size()>0){
//				mnv.addObject("lists", lists);
//			}
//		}
//		return mnv;
//	}
	@RequestMapping("/list")
	public ModelAndView list(Integer pageSize,Integer page,Integer userId,String userName,Integer permission,HttpServletRequest request){
		
		ModelAndView mnv = null;
		if(((UserInfo)request.getSession().getAttribute("user")).getPermission()!=0){ //判断当前用户是否有权限
			if(page == null)
				page = 1;
			if(pageSize == null)
				pageSize = 10;
			
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(userId);
			userInfo.setPermission(permission);
			userInfo.setUserName(userName);
			
			Pager<UserInfo> pager = userInfoService.findByExample(userInfo,page,pageSize);
			List<UserInfo> lists = pager.getReusltLists();
			mnv = new ModelAndView("user/userList");
			mnv.addObject("lists", lists);
			mnv.addObject("page", page);
			mnv.addObject("lastPage",pager.getTotalPage());
			
			mnv.addObject("userId", userId);
			mnv.addObject("userName", userName);
			mnv.addObject("permission", permission);
		}
		return mnv;
	}
	
	@RequestMapping("/query")
	public ModelAndView query(Integer userId,String userName,Integer permission,HttpSession session) {
		
		ModelAndView mnv = null;
		if(((UserInfo)session.getAttribute("user")).getPermission()!=0){
			UserInfo userInfo = new UserInfo();
			
			if(userId!=null)
				userInfo.setUserId(userId);
			userInfo.setUserName(userName);
			userInfo.setPermission(permission);
			
			List<UserInfo> lists = null;
			
			lists = userInfoService.findByExample(userInfo);
			
			mnv = new ModelAndView("user/userList");
			if(lists!=null&&lists.size()>0)
				mnv.addObject("lists", lists);
			
		}
		return mnv;
	}
	
	@RequestMapping("/delete")		
	public String delete(Integer id,HttpServletRequest request){
		
		if(((UserInfo)request.getSession().getAttribute("user")).getPermission()==2) //判断当前用户是否有权限
			if(id!=null)
				userInfoService.delete(id);
		return "redirect:list";
	}
	
	@RequestMapping(value = "/edit")
	public ModelAndView edit(Integer id){
		
		UserInfo userInfo = userInfoService.findById(id);
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("userInfo", userInfo);
		mnv.setViewName("user/userEdit");
		return mnv;
	}
	
	@RequestMapping(value = "/editSubmit",method = { RequestMethod.POST})
	public ModelAndView editSubmit(Integer id,Integer permission,HttpServletRequest request){
		
		ModelAndView mnv = null;
		if(((UserInfo)request.getSession().getAttribute("user")).getPermission()==2){  //判断当前用户是否有权限
			if(id!=null){
				UserInfo userInfo = userInfoService.findById(id);
				
				userInfo.setPermission(permission);
				userInfoService.update(userInfo);
				
				mnv = new ModelAndView();
				mnv.setViewName("redirect:list");
			}
		}
		return mnv;
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request){
		
		if(((UserInfo)request.getSession().getAttribute("user")).getPermission()==2)
			return "user/userAdd";
		return null;
	}
	
	@RequestMapping("/saveSubmit")
	public String saveSubmit(Integer id,String username,String password,
			Integer permission,String email,String desp,HttpServletRequest request){
		
		if(((UserInfo)request.getSession().getAttribute("user")).getPermission()==2){
			if(!userInfoService.isUserExist(username))
			{
				UserInfo userInfo = new UserInfo();
				userInfo.setUserName(username);
				userInfo.setUserPassword(password);
				userInfo.setPermission(permission);
				userInfo.setDescription(desp);
				userInfo.setEmail(email);
				userInfo.setCreateDate(new Date());
				userInfoService.save(userInfo);
				return "redirect:list";
			}
			return "redirect:save";
		}
		return null;
	}
	
	@RequestMapping("/personEdit")
	public String personEdit(){
		return "user/personEdit";
	}
	
	@RequestMapping("/personEditSubmit")
	public @ResponseBody Map<String,Object> personEdiSubmit(String email,String username,String desp,HttpServletResponse response,HttpSession session) throws IOException{
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("editSuccess", false);
		UserInfo user = (UserInfo)session.getAttribute("user");				//获取session中的user对象，进行修改
		if(username!=null&&!username.equals("")){
			if(!user.getUserName().equals(username))
				if(userInfoService.isUserExist(username)){
					jsonMap.put("msg", "用户名已存在!");
					return jsonMap;
				}
			user.setUserName(username);
			user.setEmail(email);
			user.setDescription(desp);
			
			userInfoService.update(user);
			session.setAttribute("user", user);
			jsonMap.put("msg", "修改成功!");
			jsonMap.put("editSuccess", true);
		}
		else
			jsonMap.put("msg", "用户名不能为空!");
		return jsonMap;
	}
	
	@RequestMapping("/passwordEdit")
	public String passwordEdit(){
		return "user/passwordEdit";
	}
	@RequestMapping("/passwordEditSubmit")
	public @ResponseBody Map<String,Object> passwordEditSubmit(HttpSession session,String oldpsw,@RequestParam("psw1") String newpsw){
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("editSuccess", false);
		UserInfo user = (UserInfo) session.getAttribute("user");
		if(user.getUserPassword().equals(oldpsw)){
			if(newpsw!=null&&!newpsw.equals("")){
				user.setUserPassword(newpsw);
				userInfoService.update(user);
				session.setAttribute("user", user);
				
				jsonMap.put("msg", "密码修改成功!");
				jsonMap.put("editSuccess", true);					//修改成功，存入map，传给前台
			}
		}
		else{
			jsonMap.put("msg", "密码错误，请重新输入！");
		}
		return jsonMap;
	}
	
	@RequestMapping("/validateUsername")
	public @ResponseBody String validateUsername(String username){
		String msg = null;
		if(userInfoService.isUserExist(username))
			msg="用户名已存在！";
		return msg;
	}
	@RequestMapping("/validateUsername1")
	public @ResponseBody String validateUsername1(String username,HttpSession session){
		UserInfo user = (UserInfo) session.getAttribute("user");
		String msg = null;
		if(user.getUserName().equals(username))		//传入用户名与当前登陆用户名一样，不需要从数据库判断
			return msg;
		else{	//传入用户名与当前用户名不一样，需要从数据库判断用户名是否已存在
			if(userInfoService.isUserExist(username))
				msg="用户名已存在！";
		}
		return msg;
	}
	
	public UserInfoService getUserInfoService() {
		return userInfoService;
	}

	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	
}
