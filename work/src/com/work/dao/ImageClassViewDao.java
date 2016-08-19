package com.work.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.work.base.BaseDao;
import com.work.entity.ImageClassView;

@Repository
public class ImageClassViewDao extends BaseDao<ImageClassView>{
	
	@SuppressWarnings("unchecked")
	public List<ImageClassView> findAll(){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		List<ImageClassView> lists = session.createQuery("select i from ImageClassView as i").list();
		session.close();
		return lists;
	}
}
