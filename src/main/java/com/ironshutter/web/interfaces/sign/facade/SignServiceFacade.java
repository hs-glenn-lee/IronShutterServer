package com.ironshutter.web.interfaces.sign.facade;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.ironshutter.web.interfaces.sign.facade.dto.AccountDTO;
import com.ironshutter.web.interfaces.sign.facade.dto.SignInForm;
import com.ironshutter.web.interfaces.sign.facade.dto.SignUpForm;
import com.ironshutter.web.interfaces.sign.facade.internal.support.SignedInValue;

public interface SignServiceFacade {
	
	public void signup(SignUpForm signUpForm);
	
	public boolean isOccupiedUsername(String username);
	
	public Optional<AccountDTO> signinAndGetAccountDTO(SignInForm signInForm, HttpSession session);
	
	public boolean signout(HttpSession session);
	
	public boolean isSignedin(HttpSession session);
	
	public Optional<SignedInValue> getSignedInValue(HttpSession session);
	
}
