package com.jsf.bean;

import org.springframework.beans.factory.annotation.Autowired;

import com.jsf.facade.Facade;

public abstract class BaseBean {

	@Autowired
	private Facade facade;

	public Facade getFacade() {
		return facade;
	}

	public void setFacade(Facade facade) {
		this.facade = facade;
	}

}
