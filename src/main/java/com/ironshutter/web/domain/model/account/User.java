package com.ironshutter.web.domain.model.account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="account_id")
	private Long accountId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@MapsId
	@OneToOne(mappedBy="user", fetch = FetchType.LAZY)
	@JoinColumn(name="account_id")
    private Account account;
	
	public User() {}
	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public Account getAccount() {
		return account;
	}
	
}
