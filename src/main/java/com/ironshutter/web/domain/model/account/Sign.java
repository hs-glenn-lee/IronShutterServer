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
@Table(name="signs")
public class Sign {
	
	@Id
	@Column(name="account_id")
	private Long accountId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="hashed_password")
	private String hashedPassword;
	
	@MapsId
	@OneToOne(mappedBy="sign", fetch = FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;
	
	public Sign() {}
	public Sign(String username, String hashedPassword) {
		this.username = username;
		this.hashedPassword = hashedPassword;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	public String getUsername() {
		return username;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public Account getAccount() {
		return account;
	}
	
}
