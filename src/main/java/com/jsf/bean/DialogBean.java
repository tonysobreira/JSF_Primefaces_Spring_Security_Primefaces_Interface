package com.jsf.bean;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

import org.primefaces.PrimeFaces;

@ManagedBean
@SessionScoped
public class DialogBean {
	
	public void openDialog() {
		PrimeFaces.current().dialog().openDynamic("dialog1");
	}

}
