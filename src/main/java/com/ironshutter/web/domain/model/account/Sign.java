package com.ironshutter.web.domain.model.account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="signs")
public class Sign {
	
	@Id
	@Column(name="account_id")
	private String accountId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="hashed_password")
	private String hashedPassword;
	
	@OneToOne(optional=true)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	
	public Sign() {}
	public Sign(String accountId, String username, String hashedPassword) {
		this.accountId = accountId;
		this.username = username;
		this.hashedPassword = hashedPassword;
	}
	
	public String getAccountId() {
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
	
	
	
	public void setAccount(Account account) {
		if(this.account != null) {
			this.account.setSign(null);
		}
		
		this.account = account;
		if(account == null)
			return;

		if(account.getSign() != this ) {
			account.setSign(this);
		}
		
	}
	
/*	public void setAccount(Account account) {
		this.account = account;
	}*/
}
