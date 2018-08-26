package com.ironshutter.web.model.service;

import com.ironshutter.web.model.jpa.entities.Account;
import com.ironshutter.web.model.jpa.entities.AccountSetting;

public interface AccountService {
	
	/**
	 * this method would be called in context of signing up. create new account and initialize AccountSetting, 
	 * */
	public Account createNewAccount(Account account) throws IllegalStateException;
	
	/**
	 * match account with username and password. if authenticate successfully return Account, else return null.
	 * */
	public Account authenticate(String username, String password);
	
	/**
	 * is parameter username unique as new Account
	 * */
	public boolean isUniqueNewUsername(String username);
	

	
	/**
	 * get Account that has public information of.
	 * */
	public AccountSetting getPublicAccountSetting(String username);

}
