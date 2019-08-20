package com.jsf.facade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jsf.constants.Constants;
import com.jsf.model.Authority;
import com.jsf.model.User;
import com.jsf.service.AuthorityService;
import com.jsf.service.UserService;

@Service("facade")
public class FacadeImpl implements Facade {

	private UserService userService;
	private AuthorityService authorityService;

	@Autowired
	public FacadeImpl(@Qualifier("userService") final UserService userService, @Qualifier("authorityService") final AuthorityService authorityService) {
		this.userService = userService;
		this.authorityService = authorityService;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public User saveUser(User user) {
		
		Set<Authority> authorities = new HashSet<Authority>();
		
		Authority authority = findByAuthority(Constants.ROLE_USER);
		
		authorities.add(authority);
		
		user.setAuths(authorities);
		
		User u = this.userService.saveUser(user);
		
		return u;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public List<User> listUser() {
		return this.userService.listUser();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public void deleteUser(User user) {
		this.userService.deleteUser(user);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public List<Authority> findAllAuthority() {
		return authorityService.findAllAuthority();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Authority findByAuthority(String authority) {
		return authorityService.findByAuthority(authority);
	}
	
	@Override
	public Authority findAuthorityById(Integer id) {
		return authorityService.findById(id);
	}

	@Override
	public Authority save(Authority authority) {
		return authorityService.save(authority);
	}

	@Override
	public User updateUser(User user) {
		return this.userService.updateUser(user);
	}

}
