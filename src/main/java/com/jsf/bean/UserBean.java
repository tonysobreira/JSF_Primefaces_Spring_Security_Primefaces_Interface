package com.jsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jsf.model.User;
import com.jsf.util.CryptoUtil;

@ManagedBean
@SessionScoped
public class UserBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -54255255545386196L;
	private User user;
	private String email;
	private String password1;
	private String password2;

	private String name;
	private String lastName;
	
	private List<User> listUser;
	

	public UserBean() {
		user = new User();
	}

	public List<User> listUser() {
		
		if (listUser == null || listUser.isEmpty()) {
			listUser = getFacade().listUser();
		}
		
		return listUser;
	}

	public void saveUser() {
		user = new User();

		user.setEmail(this.email);

		user.setAccountNonExpired(Boolean.FALSE);
		user.setAccountNonLocked(Boolean.FALSE);
		user.setCredentialsNonExpired(Boolean.FALSE);
		user.setEnabled(Boolean.TRUE);

		user.setName(this.name);
		user.setLastName(this.lastName);

		String pass = this.password1;

		try {
			pass = CryptoUtil.encryptAES(pass);
			user.setPassword(pass);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		Set<Authority> authorities = new HashSet<Authority>();
//		Authority authority = null;
//
//		authority = getFacade().findAuthorityById(new Integer(2));
//		authorities.add(authority);
//		
//		user.setAuths(authorities);

		getFacade().saveUser(user);
		user = new User();
		
		listUser = null;
	}

	public void deleteUser(User user) {
		getFacade().deleteUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			return SecurityContextHolder.getContext().getAuthentication().getName();
		}

		return "";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

}
