package com.work.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.dao.ImageChangeDao;
import com.work.entity.ImageChange;

@Service
public class ImageChangeService {

	@Resource
	private ImageChangeDao imageChangeDao;

	public void save(ImageChange imageChange){
		imageChangeDao.save(imageChange);
	}
	
	public ImageChange findById(Integer id){
		return imageChangeDao.findById(id);
	}
	
	public List<ImageChange> findAll(){
		return imageChangeDao.findAll();
	}

	public ImageChangeDao getImageChangeDao() {
		return imageChangeDao;
	}

	public void setImageChangeDao(ImageChangeDao imageChangeDao) {
		this.imageChangeDao = imageChangeDao;
	}
}
