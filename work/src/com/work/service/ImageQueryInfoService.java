package com.work.service;


import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.dao.ImageQueryInfoDao;
import com.work.entity.ImageQueryInfo;

@Service
public class ImageQueryInfoService {

	@Resource
	private ImageQueryInfoDao imageQueryInfoDao;

	public int save(ImageQueryInfo imageLog){
		return imageQueryInfoDao.save(imageLog);
	}
	
	public ImageQueryInfo findById(Integer id){
		return imageQueryInfoDao.findById(id);
	}
	
	public void delete(Integer id){
		if(id!=null)
			imageQueryInfoDao.delete(id);
	}
	
	public void deleteImageAndFile(Integer id,String path){
		ImageQueryInfo imageQueryInfo = imageQueryInfoDao.findById(id);
		if(imageQueryInfo!=null){
			this.delete(id);
			String realPath = path + imageQueryInfo.getImageUploadingAddress();
			File file = new File(realPath);
			if(file.exists())
				file.delete();
		}
	}

	public void deleteImagesAndFiles(Integer[] ids, String path) {
		List<ImageQueryInfo> lists = findByIds(ids);
		if(lists!=null&&lists.size()>0){
			for (ImageQueryInfo imageQueryInfo : lists) {
				this.delete(imageQueryInfo.getQueryId());
				String realPath = path + imageQueryInfo.getImageUploadingAddress();
				File file = new File(realPath);
				if(file.exists())
					file.delete();
			}
		}
	}
	
	public List<ImageQueryInfo> findByIds(Integer []ids){
		if (ids == null || ids.length == 0){
			return null;
		}
		return imageQueryInfoDao.findByIds(ids);
	}
	
	public Integer findMaxId(){
		return imageQueryInfoDao.findMaxId();
	}

	public void update(ImageQueryInfo imageQueryInfo) {
		imageQueryInfoDao.update(imageQueryInfo);
	}
}
