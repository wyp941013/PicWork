package com.work.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.dao.ImageLogDao;
import com.work.entity.ImageLog;
import com.work.util.Pager;

@Service
public class ImageLogService {

	@Resource
	private ImageLogDao imageLogDao;

	public void save(ImageLog imageLog){
		imageLogDao.save(imageLog);
	}
	
	public ImageLog findById(Integer id){
		return imageLogDao.findById(id);
	}
	
	public List<ImageLog> findAll(){
		return imageLogDao.findAll();
	}

	public ImageLogDao getImageLogDao() {
		return imageLogDao;
	}

	public void setImageLogDao(ImageLogDao imageLogDao) {
		this.imageLogDao = imageLogDao;
	}

	public Pager<ImageLog> findByExample(ImageLog imageLog, Integer page, Integer pageSize) {
		if(imageLog!=null)
			return imageLogDao.findByExample(imageLog,page,pageSize);
		return null;
	}

	public List<ImageLog> findPage(Integer page, int pageSize) {
		return imageLogDao.findPage(page,pageSize);
	}

	public Long findTotalNum() {
		return imageLogDao.findTotalNum();
	}
	
	
}
