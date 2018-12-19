package com.ironshutter.web.domain.model.account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	private String accountId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@OneToOne(optional=true)
	@JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
	
	public User() {}
	public User(String accountId, String name, String email) {
		this.accountId = accountId;
		this.name = name;
		this.email = email;
	}
	
	public String getAccountId() {
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
	
	
	public void setAccount(Account account) {
		if(this.account != null) {
			this.account.setUser(null);
		}
		
		this.account = account;
		if(account == null)
			return;

		if(account.getUser() != this ) {
			account.setUser(this);
		}

	}
	
	
/*	public void setAccount(Account account) {
		this.account = account;
	}*/
}
