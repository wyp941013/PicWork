package com.work.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.dao.ImageUploadViewDao;
import com.work.entity.ImageUploadView;

@Service
public class ImageUploadViewService {

	@Resource
	private ImageUploadViewDao imageUploadViewDao;

	public List<ImageUploadView> findAll(){
		return imageUploadViewDao.findAll();
	}
	public ImageUploadViewDao getImageUploadViewDao() {
		return imageUploadViewDao;
	}

	public void setImageUploadViewDao(ImageUploadViewDao imageUploadViewDao) {
		this.imageUploadViewDao = imageUploadViewDao;
	}

	
}
