package com.work.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.work.dao.UserInfoDao;
import com.work.entity.UserInfo;
import com.work.util.Pager;

@Service
public class UserInfoService {

	@Resource
	private UserInfoDao userInfoDao;

	public void save(UserInfo userInfo){
		userInfoDao.save(userInfo);
	}
	
	public void delete(Integer id){
		userInfoDao.delete(id);
	}
	
	public void update(UserInfo userInfo){
		userInfoDao.update(userInfo);
	}
	
	public UserInfo findById(Integer id){
		return userInfoDao.findById(id);
	}
	
	public List<UserInfo> findAll(){
		return userInfoDao.findAll();
	}
	
	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public UserInfo login(String username, String password){
		if(username==""||username==null||password==""||password==null)
			return null;
		return userInfoDao.login(username,password);
	}

	public boolean isUserExist(String username) {
		@SuppressWarnings("rawtypes")
		List lists = userInfoDao.findByUsername(username);
		if(lists!=null&&lists.size()>0)
			return true;
		return false;
	}

	public List<UserInfo> findByExample(UserInfo userInfo) {
		if(userInfo!=null)
			return userInfoDao.findByExample(userInfo);
		return null;
	}

	public Pager<UserInfo> findByExample(UserInfo userInfo, Integer page,
			Integer pageSize) {
		if(userInfo!=null)
			return userInfoDao.findByExample(userInfo,page,pageSize);
		return null;
	}
}
