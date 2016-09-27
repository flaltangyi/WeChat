package cn.ty.api.dao;

import java.util.List;

import cn.ty.model.User;

public interface UserDao {

	public void insertUser(User user);
	
	public List<User> findAll();
}
