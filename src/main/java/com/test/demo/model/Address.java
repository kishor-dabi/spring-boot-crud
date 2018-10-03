package com.test.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	int id;
	@Column
	String addess;
	@Column
	String state;
	
    @OneToOne(mappedBy = "address")
//    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    
    
	public Address() {
		super();
	}

	public Address( String addess, String state, User user) {
		super();
//		this.id = id;
		this.addess = addess;
		this.state = state;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddess() {
		return addess;
	}

	public void setAddess(String addess) {
		this.addess = addess;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
