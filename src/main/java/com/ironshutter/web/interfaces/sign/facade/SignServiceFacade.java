package com.ironshutter.web.interfaces.sign.facade;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.ironshutter.web.domain.model.account.Account;
import com.ironshutter.web.domain.model.account.SignInSpecification;
import com.ironshutter.web.domain.model.account.SignUpSpecification;
import com.ironshutter.web.infrastructure.httpSession.SignedInValue;
import com.ironshutter.web.interfaces.exceptions.NotSignedInException;

public interface SignServiceFacade {
	
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
