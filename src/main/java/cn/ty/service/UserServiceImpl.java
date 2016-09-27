package cn.ty.service;

import cn.ty.api.dao.UserDao;
import cn.ty.api.service.UserService;
import cn.ty.model.User;

public class UserServiceImpl implements UserService{
	private UserDao userDao;
	
	@Override
	public void newUser(User user) {
		// TODO Auto-generated method stub
		userDao.insertUser(user); 
	}

	public UserDao getUserDao() {  
		return userDao;
	}
	
	public void setUserDao(UserDao userDao) {  
		this.userDao = userDao;  
	}  
}
