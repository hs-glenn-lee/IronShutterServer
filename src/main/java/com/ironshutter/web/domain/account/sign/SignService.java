package com.ironshutter.web.domain.account.sign;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.ironshutter.web.domain.account.Account;
import com.ironshutter.web.domain.account.support.SignInForm;
import com.ironshutter.web.domain.account.support.SignUpForm;
import com.ironshutter.web.exceptions.NotSignedInException;

public interface SignService {
	
	public Account signup(SignUpForm signUpForm) throws IOException;
	
	public boolean isOccupiedUsername(String username);
	
	/**
	 * if not valid username and password, this will return null
	 * */
	public Account signin(SignInForm signInForm, HttpSession httpSession);
	
	public boolean signout(HttpSession session);
	
	public boolean isSignedin(HttpSession httpSession);
	
	public SessionSign getSign(HttpSession session) throws NotSignedInException;
}
