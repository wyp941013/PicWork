package com.work.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.work.base.BaseDao;
import com.work.entity.ImageUploadView;

@Repository
public class ImageUploadViewDao extends BaseDao<ImageUploadView>{
	
	@SuppressWarnings("unchecked")
	public List<ImageUploadView> findAll(){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		List<ImageUploadView> lists = session.createQuery("select i from ImageUploadView as i").list();
		session.close();
		return lists;
	}
}
