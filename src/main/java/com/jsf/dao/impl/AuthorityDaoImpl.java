package com.jsf.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jsf.dao.AbstractJpaRepository;
import com.jsf.dao.AuthorityDao;
import com.jsf.model.Authority;

@Repository
public class AuthorityDaoImpl  extends AbstractJpaRepository<Authority, Integer> implements AuthorityDao  {

	@Override
	public Authority save(Authority authority) {
		return this.create(authority);
	}

	@Override
	public List<Authority> findAllAuthority() {
		String jpql = " SELECT a FROM Authority a ";
		Query query = this.getEntityManager().createQuery(jpql);
		return (List<Authority>) query.getResultList();
	}

	@Override
	public Authority findAuthorityById(Integer id) {
		return this.getById(Authority.class, id);
	}

	@Override
	public Authority findByAuthority(String role) {
		StringBuilder jpql = new StringBuilder();
		
		jpql.append(" SELECT a FROM Authority a ");
		jpql.append(" where a.authority = :authority ");
		
		Query query = this.getEntityManager().createQuery(jpql.toString());
		
		query.setParameter("authority", role);
		
		Authority a = (Authority) query.getSingleResult();
		return a;
	}

}
