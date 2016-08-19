package com.work.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.work.base.BaseDao;
import com.work.entity.ImageInfo;
import com.work.entity.ImageQueryInfo;
import com.work.entity.ImageResultInfo;
import com.work.util.Pager;

@Repository
public class ImageInfoDao extends BaseDao<ImageInfo>{

	
	public void batchInsert(List<ImageInfo> lists){
		HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
		Session session = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			session = hibernateTemplate.getSessionFactory().openSession();
			conn = session.connection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into image_info (image_despretion,image_classify_id,image_address,image_rank,image_format,create_time,editor_user_id)" +
					"values(?,?,?,?,?,?,?)");
			for(ImageInfo ii : lists){
				ps.setString(1, ii.getImageDespretion());
				ps.setInt(2, ii.getImageClassify().getImageClassifyId());
				ps.setString(3, ii.getImageAddress());
				ps.setInt(4, ii.getImageRank());
				ps.setString(5, ii.getImageFormat());
				ps.setDate(6, new java.sql.Date(ii.getCreateTime().getTime()));
				ps.setInt(7, ii.getEditorUserId());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			if(session!=null)
				session.close();
			try {
				if(conn!=null)
					conn.close();
				if(ps!=null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@SuppressWarnings("unchecked")
	public Pager<ImageInfo> findByExample(ImageInfo imageInfo,Integer page, Integer pageSize) {
		List<ImageInfo> lists = null;
		Pager<ImageInfo> pager = new Pager<ImageInfo>();
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		Criteria criteria = session.createCriteria(ImageInfo.class);
		if(imageInfo.getImageClassify()!=null)
			criteria.add(Restrictions.eq("imageClassify",imageInfo.getImageClassify()));
		if(imageInfo.getImageDespretion()!=null&&!imageInfo.getImageDespretion().equals(""))
			criteria.add(Restrictions.like("imageDespretion","%"+imageInfo.getImageDespretion()+"%"));
		if(imageInfo.getEditorUserId()!=null)
			criteria.add(Restrictions.eq("editorUserId",imageInfo.getEditorUserId()));
		if(imageInfo.getCreateTime()!=null)
			criteria.add(Restrictions.eq("createTime",imageInfo.getCreateTime()));
		
		// 获取根据条件分页查询的总行数  
        int totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();  
        criteria.setProjection(null);  
        
        criteria.addOrder(Order.desc("imageId"));
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
	public Pager<ImageResultInfo> findResultList(ImageResultInfo imageResultInfo,Integer page, Integer pageSize) {
		List<ImageResultInfo> lists = null;
		Pager<ImageResultInfo> pager = new Pager<ImageResultInfo>();
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		Criteria criteria = session.createCriteria(ImageResultInfo.class);
		if(imageResultInfo.getQueryId()!=null)
			criteria.add(Restrictions.eq("queryId",imageResultInfo.getQueryId()));
		
		// 获取根据条件分页查询的总行数  
        int totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();  
        criteria.setProjection(null);  
        
        criteria.addOrder(Order.desc("resultId"));
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
	public Pager<ImageQueryInfo> findQueryList(Integer page, Integer pageSize) {
		List<ImageQueryInfo> lists = null;
		Pager<ImageQueryInfo> pager = new Pager<ImageQueryInfo>();
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		Criteria criteria = session.createCriteria(ImageQueryInfo.class);
		criteria.setCacheable(true);
		
		// 获取根据条件分页查询的总行数  
		int totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();  
		criteria.setProjection(null);  
		
		criteria.addOrder(Order.desc("queryId"));
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
	@Override
	public List<ImageInfo> findAll() {
		
		return this.getHibernateTemplate().find("from ImageInfo order by imageId desc");
	}

	@SuppressWarnings("unchecked")
	public List<ImageInfo> findPage(Integer page, Integer pageSize) {
		List<ImageInfo> lists = null;
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		lists = session.createQuery("from ImageInfo order by imageId desc").setMaxResults(pageSize).setFirstResult((page-1)*pageSize).list();
		session.close();
		return lists;
	}

	public Long findTotalNum() {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Long total = (Long)session.createQuery("select count(*) from ImageInfo").uniqueResult();
		session.close();
		return total;
	}
	
	public Integer findMaxId(){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		String sql = "SELECT t.`AUTO_INCREMENT` FROM information_schema.`TABLES` t WHERE   t.`TABLE_NAME`='image_info' and t.TABLE_SCHEMA='work'";
		BigInteger max = (BigInteger)session.createSQLQuery(sql).uniqueResult();
		session.close();
		return max.intValue();
	}
	
	public Date findMaxDate(){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		String hql = "select max(createTime) from ImageInfo";
		Date maxDate = (Date)session.createQuery(hql).uniqueResult();
		session.close();
		return maxDate;
	}
	
}
