package com.jsf.dao;

import java.util.List;

import com.jsf.model.User;

public interface UserRepository {

	public User saveUser(User user);

	public List<User> listUser();

	public void deleteUser(User user);
	
	public User findByUsername(String username);
	
	public User updateUser(User user);
	
}
