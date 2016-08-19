package com.work.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.work.base.BaseDao;
import com.work.entity.ImageQueryInfo;

@Repository
public class ImageQueryInfoDao extends BaseDao<ImageQueryInfo>{
	
	public Integer findMaxId(){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		String hql = "select max(queryId) from ImageQueryInfo";
		Integer max = (Integer)session.createQuery(hql).uniqueResult();
		session.close();
		return max;
	}
	
}
