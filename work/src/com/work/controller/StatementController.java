package com.work.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.work.entity.ImageClassView;
import com.work.entity.ImageUploadView;
import com.work.service.ImageClassViewService;
import com.work.service.ImageClassifyService;
import com.work.service.ImageUploadViewService;

@Controller
@RequestMapping("/statement")
public class StatementController {
	@Autowired
	private ImageClassifyService imageClassifyService;
	@Autowired
	private ImageClassViewService imageClassViewService;
	@Autowired
	private ImageUploadViewService imageUploadViewService;
	
	@RequestMapping("/imageClass")
	public String imageClass(){
		return "statement/imageClass";
	}
	@RequestMapping("/getImageClassStatement")
	public @ResponseBody Map<String, Object> getImageClassStatement(){
		Map<String, Object> jsonMap = new HashMap<String,Object>();
	
		List<ImageClassView> lists = imageClassViewService.findAll();
		
		List<String> name = new ArrayList<String>();
		List<Object> value = new ArrayList<Object>();
		int total = 0;
		
		for (ImageClassView imageClassView : lists) {
			name.add(imageClassView.getImageClassifyName());
			value.add(imageClassView.getNum());
			total+=imageClassView.getNum();
		}
		jsonMap.put("name", name);
		jsonMap.put("value", value);
		jsonMap.put("total", total);
		return jsonMap;
	}
	
	@RequestMapping("/imageUpload")
	public String imageUpload(){
		return "statement/imageUpload";
	}
	@RequestMapping("/getImageUploadStatement")
	public @ResponseBody Map<String, Object> getImageUploadStatement(){
		Map<String, Object> jsonMap = new HashMap<String,Object>();
	
		List<ImageUploadView> lists = imageUploadViewService.findAll();
		
		List<String> name = new ArrayList<String>();
		List<Object> value = new ArrayList<Object>();
		int total = 0;
		
		for (ImageUploadView imageUploadView : lists) {
			name.add(imageUploadView.getUserName());
			value.add(imageUploadView.getNum());
			total+=imageUploadView.getNum();
		}
		jsonMap.put("name", name);
		jsonMap.put("value", value);
		jsonMap.put("total", total);
		return jsonMap;
	}

	public ImageClassifyService getImageClassifyService() {
		return imageClassifyService;
	}

	public void setImageClassifyService(ImageClassifyService imageClassifyService) {
		this.imageClassifyService = imageClassifyService;
	}

	public ImageClassViewService getImageClassViewService() {
		return imageClassViewService;
	}

	public void setImageClassViewService(ImageClassViewService imageClassViewService) {
		this.imageClassViewService = imageClassViewService;
	}
	public ImageUploadViewService getImageUploadViewService() {
		return imageUploadViewService;
	}
	public void setImageUploadViewService(
			ImageUploadViewService imageUploadViewService) {
		this.imageUploadViewService = imageUploadViewService;
	}
	
	
}
