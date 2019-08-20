package com.jsf.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.RequestContext;

import com.jsf.util.CryptoUtil;

@ManagedBean
@SessionScoped
public class LoginBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 7413858190206623738L;

	private String email;
	private String password;

	@Autowired
	private AuthenticationManager authenticationManager;

	public void login() {

		String encPass = CryptoUtil.encryptAES(this.password);
		String decPass = CryptoUtil.decryptAES(encPass);

		System.out.println("Encrypted Password: " + encPass);
		System.out.println("Decrypted Password: " + decPass);
		
		try {
			
		Authentication authRequest = new UsernamePasswordAuthenticationToken(this.email, this.password);
		Authentication authentication = authenticationManager.authenticate(authRequest);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("../secure/dashboard.xhtml");
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadCredentialsException be) {
			FacesContext context = FacesContext.getCurrentInstance();
	         
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning",  "Wrong Login/Password"));
	        
			//FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Wrong Login/Password");
			//PrimeFaces.current().dialog().showMessageDynamic(message);
			
			be.printStackTrace();
		}

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
