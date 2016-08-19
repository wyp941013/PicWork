package com.work.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.work.base.BaseDao;
import com.work.entity.UserInfo;
import com.work.util.Pager;

@Repository
public class UserInfoDao extends BaseDao<UserInfo> {

	public UserInfo login(String username, String password) {
		UserInfo userInfo = null;
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Query query = session
				.createQuery("from UserInfo u where u.userName =? and u.userPassword=?");
		query.setParameter(0, username);
		query.setParameter(1, password);
		@SuppressWarnings("unchecked")
		List<UserInfo> lists = query.list();
		if (lists != null && lists.size() > 0)
			userInfo = lists.get(0);
		session.close();
		return userInfo;
	}

	@SuppressWarnings("rawtypes")
	public List findByUsername(String username) {
		return this.getHibernateTemplate().find(
				"from UserInfo where userName = ?", username);
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> findByExample(UserInfo userInfo) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();

		Criteria criteria = session.createCriteria(UserInfo.class);

		List<UserInfo> lists = null;

		if (userInfo.getUserId() != null)
			criteria.add(Restrictions.eq("userId", userInfo.getUserId()));
		if (userInfo.getUserName() != null)
			criteria.add(Restrictions.like("userName",
					"%" + userInfo.getUserName() + "%"));
		if (userInfo.getPermission() != null)
			criteria.add(Restrictions.eq("permission", userInfo.getPermission()));

		lists = criteria.list();

		session.close();
		return lists;
	}
	
	@SuppressWarnings("unchecked")
	public Pager<UserInfo> findByExample(UserInfo userInfo, Integer page,
			Integer pageSize) {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();

		Pager<UserInfo> pager = new Pager<UserInfo>();
		Criteria criteria = session.createCriteria(UserInfo.class);

		List<UserInfo> lists = null;

		if (userInfo.getUserId() != null)
			criteria.add(Restrictions.eq("userId", userInfo.getUserId()));
		if (userInfo.getUserName() != null)
			criteria.add(Restrictions.like("userName",
					"%" + userInfo.getUserName() + "%"));
		if (userInfo.getPermission() != null)
			criteria.add(Restrictions.eq("permission", userInfo.getPermission()));

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

}
