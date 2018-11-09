package com.test.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Long id;
	@Column
	private String name;
	@Column
	private String number;
	
	@Column(unique = true)
	private String email;
	
    
	@OneToOne(cascade = {CascadeType.ALL, CascadeType.REMOVE})
    @JoinColumn(name = "address_id")
	private Address address;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<Account> accounts;
	
	
	public User() {
		super();
	}
	public User(/*Long id,*/ String name, String number,String email) {
		super();
//		this.id = id;
		this.name = name;
		this.number = number;
		this.email = email;
	}
	
	public User(/*Long id,*/ String name, String number,String email, Address address,List<Account> accounts) {
		super();
//		this.id = id;
		this.name = name;
		this.number = number;
		this.address = address;
		this.accounts = accounts;
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name +", email=" + email+ ", number=" + number + ", address=" + address + ", accounts="
				+ accounts + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
