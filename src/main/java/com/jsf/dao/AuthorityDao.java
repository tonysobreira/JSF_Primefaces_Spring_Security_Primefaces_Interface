package com.jsf.dao;

import java.util.List;

import com.jsf.model.Authority;

public interface AuthorityDao {

	public Authority save(Authority authority);

	public List<Authority> findAllAuthority();

	public Authority findAuthorityById(Integer id);

	public Authority findByAuthority(String role);

}