package com.ironshutter.web.model.service;

import java.io.Serializable;

import com.ironshutter.web.model.jpa.entities.Account;

/**
 * Sign instance is saved in session when user sign in.
 * */
public class Sign implements Serializable{
	
	private static final long serialVersionUID = 6776507590445171404L;
	
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
