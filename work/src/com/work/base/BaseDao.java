package com.work.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

@SuppressWarnings("unchecked")
public class BaseDao<T> {
	
	@Resource
	private HibernateTemplate hibernateTemplate;
	private Class<T> clazz;
	
	public BaseDao(){
		//使用反射技术得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); // 获取当前new的对象的 泛型的父类 类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
	}
	
	public int save(T entity){
		return (Integer) this.hibernateTemplate.save(entity);
	}

	public void delete(Integer id){
		Object entity = findById(id);
		this.hibernateTemplate.delete(entity);
	}
	
	public void update(T entity){
		this.hibernateTemplate.update(entity);
	}
	
	public T findById(Integer id){
		return (T)this.hibernateTemplate.get(clazz, id);
	}
	
	public List<T> findAll(){
		String queryString = "from "+clazz.getSimpleName();
		return this.hibernateTemplate.find(queryString);
	}
	
	public List<T> findByIds(Integer[] ids) {
		Session session = hibernateTemplate.getSessionFactory().openSession();
		List<T> lists= session.createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
				.setParameterList("ids", ids)//
				.list();
		session.close();
		return lists;
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	
}
