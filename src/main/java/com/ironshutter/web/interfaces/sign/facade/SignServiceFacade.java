package com.ironshutter.web.domain.account.sign;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.ironshutter.web.domain.account.Account;
import com.ironshutter.web.domain.account.sign.support.SignInSpecification;
import com.ironshutter.web.domain.account.sign.support.SignUpSpecification;
import com.ironshutter.web.domain.account.sign.support.SignedInValue;
import com.ironshutter.web.exceptions.NotSignedInException;

public interface SignService {
	
	public void signup(SignUpSpecification signUpSpecifiaction);
	
	public boolean isOccupiedUsername(String username);
	
	/**
	 * if not valid username and password, this will return null
	 * */
	public Account signin(SignInSpecification signInForm, HttpSession httpSession);
	
	public boolean signout(HttpSession session);
	
	public boolean isSignedin(HttpSession httpSession);
	
	public SignedInValue getSign(HttpSession session) throws NotSignedInException;
}
