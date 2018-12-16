package com.ironshutter.web.domain.account.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ironshutter.web.domain.account.Account;

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
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
	
	public User() {}
}
