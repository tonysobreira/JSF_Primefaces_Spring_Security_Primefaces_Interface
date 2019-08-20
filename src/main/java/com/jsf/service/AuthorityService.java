package com.jsf.service;

import java.util.List;

import com.jsf.model.Authority;

public interface AuthorityService {

	public Authority save(Authority authority);

	public List<Authority> findAllAuthority();

	public Authority findByAuthority(String authority);

	public Authority findById(Integer id);

}
