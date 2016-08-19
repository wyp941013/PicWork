package com.work.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.dao.ImageResultInfoDao;
import com.work.entity.ImageInfo;
import com.work.entity.ImageResultInfo;
import com.work.util.Pager;

@Service
public class ImageResultInfoService {

	@Resource
	private ImageResultInfoDao imageResultInfoDao;

	public int save(ImageResultInfo imageResultInfo){
		return imageResultInfoDao.save(imageResultInfo);
	}
//	
//	public List<ImageResultInfo> findAllQueryResult(int queryId) {
//		return imageResultInfoDao.findAllQueryResult(queryId);
//	}
	
	public void delete(Integer id){
		if(id!=null)
			imageResultInfoDao.delete(id);
	}
	
	public List<ImageInfo> findByQueryId(Integer queryId){
		return imageResultInfoDao.findByQueryId(queryId);
	}
	
	public Pager<ImageInfo> findByQueryIdWithPage(Integer queryId,Integer page,Integer pageSize){
		return imageResultInfoDao.findByQueryIdWithPage(queryId, page, pageSize);
	}

	public void deleteByQueryId(Integer queryId) {
		imageResultInfoDao.deleteByQueryId(queryId);
	}
}
