package com.jsf.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 8564071903734448536L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "authority_id")
	private Integer id;

	@Column(name = "authority")
	private String authority;

	public Authority() {
	}

	public Authority(String authority) {
		this.authority = authority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Authority authority = (Authority) o;
		return Objects.equals(this.authority, authority.authority);
	}

	@Override
	public int hashCode() {
		return Objects.hash(authority);
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", authority=" + authority + "]";
	}

}