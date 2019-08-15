package com.jsf.bean;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

import com.jsf.model.User;
import com.jsf.util.CryptoUtil;

@ManagedBean
@SessionScoped
public class RegisterBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 751435123254279035L;

	private String email;
	private String password;

	public void saveUser() {
		User user = new User();

		user.setEmail(this.email);

		user.setAccountNonExpired(Boolean.FALSE);
		user.setAccountNonLocked(Boolean.FALSE);
		user.setCredentialsNonExpired(Boolean.FALSE);
		user.setEnabled(Boolean.TRUE);

		user.setName("name" + this.email);
		user.setLastName("last name" + this.email);

		String pass = this.password;

		try {
			pass = CryptoUtil.encryptAES(pass);
			user.setPassword(pass);
		} catch (Exception e) {
			e.printStackTrace();
		}

		User u = getFacade().saveUser(user);

		System.out.println(u);

		user = new User();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
