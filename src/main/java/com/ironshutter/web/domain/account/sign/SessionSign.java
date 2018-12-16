package com.ironshutter.web.domain.account.sign;

import java.io.Serializable;

import com.ironshutter.web.domain.account.Account;

/**
 * Sign instance is saved in session when user sign in.
 * */
public class SessionSign implements Serializable{
	
	private static final long serialVersionUID = 6776507590445171404L;
	
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
