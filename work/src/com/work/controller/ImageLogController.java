package com.work.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.work.entity.ImageLog;
import com.work.service.ImageClassifyService;
import com.work.service.ImageInfoService;
import com.work.service.ImageLogService;
import com.work.util.Pager;

@Controller
@RequestMapping("/imagelog")
public class ImageLogController {
	
	@Autowired
	private ImageLogService imageLogService;
	@Autowired
	private ImageInfoService imageInfoService;
	@Autowired
	private ImageClassifyService imageClassifyService;
	
	@RequestMapping("/list")
	public ModelAndView list(Integer pageSize,Integer page,Integer content,Integer editorId,String createTime) throws ParseException{
		if(page == null)
			page = 1;
		if(pageSize == null)
			pageSize = 50;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ImageLog imageLog = new ImageLog();
		
		imageLog.setContent(content);
		if(createTime!=null&&!createTime.equals(""))
			imageLog.setCreateTime(sdf.parse(createTime));
		imageLog.setEditorUserId(editorId);
		
		Pager<ImageLog> pager = imageLogService.findByExample(imageLog,page,pageSize);
		
		List<ImageLog> lists = pager.getReusltLists();
		ModelAndView mnv = new ModelAndView("log/imageLogList");
		mnv.addObject("lists", lists);
		mnv.addObject("page", page);
		mnv.addObject("lastPage",pager.getTotalPage());
		
		mnv.addObject("content", content);
		mnv.addObject("editorId", editorId);
		mnv.addObject("createTime", createTime);
		return mnv;
	}
	
	public ImageInfoService getImageInfoService() {
		return imageInfoService;
	}
	public void setImageInfoService(ImageInfoService imageInfoService) {
		this.imageInfoService = imageInfoService;
	}

	public ImageClassifyService getImageClassifyService() {
		return imageClassifyService;
	}

	public void setImageClassifyService(ImageClassifyService imageClassifyService) {
		this.imageClassifyService = imageClassifyService;
	}

	public ImageLogService getImageLogService() {
		return imageLogService;
	}

	public void setImageLogService(ImageLogService imageLogService) {
		this.imageLogService = imageLogService;
	}
	
}
