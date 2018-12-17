package com.ironshutter.web.interfaces.sign.facade;

import javax.servlet.http.HttpSession;

import com.ironshutter.web.infrastructure.httpSession.SignedInValue;
import com.ironshutter.web.interfaces.sign.dto.AccountDTO;
import com.ironshutter.web.interfaces.sign.dto.SignInForm;
import com.ironshutter.web.interfaces.sign.dto.SignUpForm;

public interface SignServiceFacade {
	
	public void signup(SignUpForm signUpForm);
	
	public boolean isOccupiedUsername(String username);
	
	public AccountDTO signin(SignInForm signInForm, HttpSession session);
	
	public boolean signout(HttpSession session);
	
	public boolean isSignedin(HttpSession session);
	
	public SignedInValue getSign(HttpSession session);
	
}
