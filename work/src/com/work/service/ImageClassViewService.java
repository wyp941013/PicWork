package com.work.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.dao.ImageClassViewDao;
import com.work.entity.ImageClassView;

@Service
public class ImageClassViewService {

	@Resource
	private ImageClassViewDao imageClassViewDao;

	
	public List<ImageClassView> findAll(){
		return imageClassViewDao.findAll();
	}

	public ImageClassViewDao getImageClassViewDao() {
		return imageClassViewDao;
	}

	public void setImageClassViewDao(ImageClassViewDao imageClassViewDao) {
		this.imageClassViewDao = imageClassViewDao;
	}
	
}
