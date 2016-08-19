package com.work.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.work.entity.UserInfo;
import com.work.util.StaticResourcesParser;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String URI = request.getRequestURI();
		
//		System.out.println(URI);
		
		//防止相关资源被拦截
		if(StaticResourcesParser.parseUrl(URI))
			return true;
		
		
//		//登陆验证请求，放行。
//		if(URI.indexOf("login")>0)
//			return true;
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		
		//user为空，表示未登陆
		if(user!=null)
		{
			return true;
		}
		System.out.println("用户未登陆！");
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return false;
	}

	
}
