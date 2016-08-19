package com.work.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.work.base.BaseDao;
import com.work.entity.ImageLog;
import com.work.util.Pager;

@Repository
public class ImageLogDao extends BaseDao<ImageLog>{

	@SuppressWarnings({ "unchecked" })
	public Pager<ImageLog> findByExample(ImageLog imageLog, Integer page, Integer pageSize) {
		
		List<ImageLog> lists = null;
		Pager<ImageLog> pager = new Pager<ImageLog>();
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(ImageLog.class);
		if(imageLog.getContent()!=null)
			criteria.add(Restrictions.eq("content", imageLog.getContent()));
		if(imageLog.getEditorUserId()!=null){
			criteria.add(Restrictions.eq("editorUserId", imageLog.getEditorUserId()));
		}
		if(imageLog.getCreateTime()!=null)
			criteria.add(Restrictions.eq("createTime", imageLog.getCreateTime()));
		
		// 获取根据条件分页查询的总行数  
        int totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();  
        criteria.setProjection(null);  
        
        //实现分页
        criteria.setFirstResult((page - 1) * pageSize);  
        criteria.setMaxResults(pageSize);  
        
		lists = criteria.list();
		pager.setReusltLists(lists);
		pager.setCurPage(page);
		pager.setPageSize(pageSize);
		pager.setTotalCount(totalCount);
		pager.setTotalPage(totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
		
		session.close();
		return pager;
	}

	@SuppressWarnings("unchecked")
	public List<ImageLog> findPage(Integer page, int pageSize) {
		List<ImageLog> lists = null;
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		lists = session.createQuery("from ImageLog").setMaxResults(pageSize).setFirstResult((page-1)*pageSize).list();
		session.close();
		return lists;
	}

	public Long findTotalNum() {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Long total = (Long)session.createQuery("select count(*) from ImageLog").uniqueResult();
		session.close();
		return total;
	}
}
