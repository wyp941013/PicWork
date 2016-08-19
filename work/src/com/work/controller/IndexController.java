package com.work.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.work.entity.ImageLog;
import com.work.entity.UserInfo;
import com.work.service.ImageInfoService;
import com.work.service.ImageLogService;
import com.work.util.IndexHelper;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	private ImageInfoService imageInfoService;
	@Autowired
	private ImageLogService imageLogService;

	@RequestMapping("/update")
	public String update(HttpServletRequest request){
		String path = request.getServletContext().getRealPath("/");
		path = path.replace("\\", "/");
		
		File file = new File(path+"/upload/feature.des");		//以此文件来判断最近一次更新索引的时间
		if(file.exists()){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date recent = new Date(file.lastModified());			//最近一次更新索引的时间
			
			String recentStr = sdf.format(recent);
			
			request.setAttribute("recent", "最近一次更新索引的时间为："+recentStr);
		}
		else
			request.setAttribute("recent", "索引文件丢失！请尽快更新索引！");
		return "index/indexUpdate";
	}
	
	@RequestMapping("/indexUpdate")
	public synchronized @ResponseBody String indexUpdate(HttpServletRequest request){
		String path = request.getServletContext().getRealPath("/");
		String state;
		
		path = path.replace("\\", "/");
		
		int flag = IndexHelper.updateIndex(path+"/upload");
		if(flag==0)
			state = "success";
		else
			state = "fail";
		
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		//日志记录
		ImageLog imageLog = new ImageLog();
		imageLog.setContent(3);					//3代表索引更新
        imageLog.setEditorUserId(user.getUserId());
        imageLog.setCreateTime(new Date());
        imageLogService.save(imageLog);			//将日志信息保存到数据库
        
        request.getServletContext().setAttribute("isIndexLatest", true);
        
		return state;
	}

	public ImageInfoService getImageInfoService() {
		return imageInfoService;
	}

	public void setImageInfoService(ImageInfoService imageInfoService) {
		this.imageInfoService = imageInfoService;
	}

	public ImageLogService getImageLogService() {
		return imageLogService;
	}

	public void setImageLogService(ImageLogService imageLogService) {
		this.imageLogService = imageLogService;
	}
	
	
}
