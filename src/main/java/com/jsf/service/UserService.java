package com.jsf.service;

import java.util.List;

import com.jsf.model.User;

public interface UserService {

	public User saveUser(User user);

	public List<User> listUser();

	public void deleteUser(User user);

	public User findByUsername(String username);
	
	public User updateUser(User user);

}
