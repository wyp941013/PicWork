package com.work.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.dao.ImageClassifyDao;
import com.work.entity.ImageClassify;

@Service
public class ImageClassifyService {

	@Resource
	private ImageClassifyDao imageClassifyDao;

	public void save(ImageClassify imageClassify){
		imageClassifyDao.save(imageClassify);
	}
	
	public void delete(Integer id){
		if(id!=null)
			imageClassifyDao.delete(id);
	}
	
	public void update(ImageClassify imageClassify){
		imageClassifyDao.update(imageClassify);
	}
	
	public ImageClassify findById(Integer id){
		return imageClassifyDao.findById(id);
	}
	
	public List<ImageClassify> findAll(){
		return imageClassifyDao.findAll();
	}
	
	public ImageClassifyDao getImageClassifyDao() {
		return imageClassifyDao;
	}

	public void setImageClassifyDao(ImageClassifyDao imageClassifyDao) {
		this.imageClassifyDao = imageClassifyDao;
	}

	@SuppressWarnings("rawtypes")
	public List<Map> findAllWithMap() {
		return imageClassifyDao.findAllWithMap();
	}

	public boolean isClassnameExists(String classname) {
		List<ImageClassify> lists = imageClassifyDao.findByClassname(classname);
		if(lists!=null&&lists.size()>0)
			return true;
		return false;
	}
	
}
