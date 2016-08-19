package com.work.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.dao.ImageInfoDao;
import com.work.entity.ImageInfo;
import com.work.entity.ImageQueryInfo;
import com.work.entity.ImageResultInfo;
import com.work.util.Pager;

@Service
public class ImageInfoService {

	@Resource
	private ImageInfoDao imageInfoDao;

	public void save(ImageInfo imageInfo) {
		imageInfoDao.save(imageInfo);
	}

	public void delete(Integer id) {
		if (id != null)
			imageInfoDao.delete(id);
	}

	public void deleteImageAndFile(Integer id, String path) {
		ImageInfo imageInfo = imageInfoDao.findById(id);
		if (imageInfo != null) {
			this.delete(id);
			String realPath = path + imageInfo.getImageAddress();
			File file = new File(realPath);
			if (file.exists())
				file.delete();
		}
	}

	public void deleteImagesAndFiles(Integer[] ids, String path) {
		List<ImageInfo> lists = findByIds(ids);
		if (lists != null && lists.size() > 0) {
			for (ImageInfo imageInfo : lists) {
				this.delete(imageInfo.getImageId());
				String realPath = path + imageInfo.getImageAddress();
				File file = new File(realPath);
				if (file.exists())
					file.delete();
			}
		}
	}

	public void update(ImageInfo imageInfo) {
		imageInfoDao.update(imageInfo);
	}

	public ImageInfo findById(Integer id) {
		return imageInfoDao.findById(id);
	}

	public List<ImageInfo> findAll() {
		return imageInfoDao.findAll();
	}

	public List<ImageInfo> findByIds(Integer[] ids) {
		if (ids == null || ids.length == 0) {
			return null;
		}
		return imageInfoDao.findByIds(ids);
	}

	public void bathInsert(List<ImageInfo> lists) {
		try{
			if (lists != null && lists.size() > 0)
				imageInfoDao.batchInsert(lists);
		}catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public ImageInfoDao getImageInfoDao() {
		return imageInfoDao;
	}

	public void setImageInfoDao(ImageInfoDao imageInfoDao) {
		this.imageInfoDao = imageInfoDao;
	}

	public Pager<ImageResultInfo> findResultList(
			ImageResultInfo imageResultInfo, Integer page, Integer pageSize) {
		if (imageResultInfo != null)
			return imageInfoDao.findResultList(imageResultInfo, page, pageSize);
		return null;
	}

	public Pager<ImageInfo> findByExample(ImageInfo imageInfo, Integer page,
			Integer pageSize) {
		if (imageInfo != null)
			return imageInfoDao.findByExample(imageInfo, page, pageSize);
		return null;
	}

	public Pager<ImageQueryInfo> findQueryList(Integer page, Integer pageSize) {
		return imageInfoDao.findQueryList(page, pageSize);
	}

	public List<ImageInfo> findPage(Integer page, Integer pageSize) {

		return imageInfoDao.findPage(page, pageSize);
	}

	public Long findTotalNum() {
		return imageInfoDao.findTotalNum();
	}

	public Integer findMaxId() {
		return imageInfoDao.findMaxId();
	}

	public Date findMaxDate() {
		return imageInfoDao.findMaxDate();
	}

}
