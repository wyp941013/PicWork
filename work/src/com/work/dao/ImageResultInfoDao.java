package com.work.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.work.base.BaseDao;
import com.work.entity.ImageInfo;
import com.work.entity.ImageResultInfo;
import com.work.util.Pager;

@Repository
public class ImageResultInfoDao extends BaseDao<ImageResultInfo>{
	

//	@SuppressWarnings("unchecked")
//	public List<ImageResultInfo> findAllQueryResult(int queryId) {
//		List<ImageResultInfo> lists = null;
//		
//		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
//		
//		Criteria criteria = session.createCriteria(ImageResultInfo.class);
//		criteria.setCacheable(true);
//		if(queryId!=0)
//			criteria.add(Restrictions.eq("queryId",queryId));
//		
//		// 获取根据条件分页查询的总行数  
//        int totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();  
//        criteria.setProjection(null);  
//        
//        criteria.addOrder(Order.desc("resultId"));
//        
//		lists = criteria.list();
//		
//		
//		session.close();
//		return lists;
//		}
	
	public List<ImageInfo> findByQueryId(Integer queryId){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		String hql =
				"select ii from ImageInfo as ii,ImageResultInfo as iri " +
				"where iri.queryId = ? and ii.imageId = iri.imageId order by iri.similarityRank";
		Query query = session.createQuery(hql);
		query.setParameter(0, queryId);
		
		@SuppressWarnings("unchecked")
		List<ImageInfo> lists = (List<ImageInfo>)query.list();
		session.close();
		return lists;
	}
	
	public Pager<ImageInfo> findByQueryIdWithPage(Integer queryId,Integer page,Integer pageSize){
		Pager<ImageInfo> pager = new Pager<ImageInfo>();
		
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		
		Integer totalCount = ((Long)session.createQuery("select count(*) from ImageResultInfo where queryId=?")
						.setParameter(0, queryId)
						.uniqueResult()).intValue();
		String hql =
				"select ii from ImageInfo as ii,ImageResultInfo as iri " +
				"where iri.queryId = ? and ii.imageId = iri.imageId order by iri.similarityRank";
		Query query = session.createQuery(hql);
		query.setParameter(0, queryId);
		query.setMaxResults(pageSize);
		query.setFirstResult((page-1)*pageSize);
		
		@SuppressWarnings("unchecked")
		List<ImageInfo> lists = (List<ImageInfo>)query.list();
		pager.setReusltLists(lists);
		pager.setCurPage(page);
		pager.setPageSize(pageSize);
		pager.setTotalCount(totalCount);
		pager.setTotalPage(totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1);
		session.close();
		return pager;
	}

	public void deleteByQueryId(Integer queryId) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		session.createQuery("delete from ImageResultInfo where queryId = ?")
		.setParameter(0, queryId)
		.executeUpdate();
		session.close();
	}
	
}
