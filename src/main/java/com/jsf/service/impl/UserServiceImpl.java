package com.jsf.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsf.dao.UserRepository;
import com.jsf.model.User;
import com.jsf.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService, Serializable {

	private static final long serialVersionUID = 4566445645512541971L;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User saveUser(User user) {
		return this.userRepository.saveUser(user);
	}

	@Transactional
	public List<User> listUser() {
		return this.userRepository.listUser();
	}

	@Transactional
	public void deleteUser(User user) {
		this.userRepository.deleteUser(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.updateUser(user);
	}

}
