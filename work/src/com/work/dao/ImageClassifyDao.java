package com.work.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.work.base.BaseDao;
import com.work.entity.ImageClassify;

@Repository
public class ImageClassifyDao extends BaseDao<ImageClassify>{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> findAllWithMap() {
		List<Map> classes = null;
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		String hql = "select distinct new map(i.imageClassifyId as id,i.imageClassifyName as text) from  ImageClassify i";
		Query query = session.createQuery(hql);
		classes = query.list();
		session.close();
		return classes;
	}

	public List<ImageClassify> findByClassname(String classname) {
		@SuppressWarnings("unchecked")
		List<ImageClassify> lists = this.getHibernateTemplate().find("from ImageClassify where imageClassifyName = ?",classname);
		return lists;
	}

}
