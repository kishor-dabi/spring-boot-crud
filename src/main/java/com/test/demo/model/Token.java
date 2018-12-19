package com.test.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "token")
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private int id;
	@Column
	private String token;
	@Column(unique = true)
	private Long userid;
	
	
	public Token() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Token(String token, Long user_id) {
		super();
		this.token = token;
		this.userid = user_id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUser_id() {
		return userid;
	}
	public void setUser_id(Long user_id) {
		this.userid = user_id;
	}
	
	
}
