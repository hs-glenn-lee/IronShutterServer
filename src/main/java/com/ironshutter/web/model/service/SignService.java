package com.ironshutter.web.model.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.ironshutter.web.exceptions.NotSignedInException;
import com.ironshutter.web.model.jpa.entities.Account;
import com.ironshutter.web.model.jpa.entities.AccountSetting;

public interface SignService {
	
	public boolean signup(Account account) throws IOException;
	
	/**
	 * if not valid username and password, this will return null
	 * */
	public Account signin(Account account, HttpSession httpSession);
	
	public boolean signout(HttpSession session);
	
	public boolean isSignedin(HttpSession httpSession);
	
	public Sign getSign(HttpSession session) throws NotSignedInException;
	
}
