package com.ironshutter.web.interfaces.sign.facade.internal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ironshutter.web.application.AccountService;
import com.ironshutter.web.domain.model.account.Account;
import com.ironshutter.web.infrastructure.httpSession.SignedInValue;
import com.ironshutter.web.interfaces.sign.dto.AccountDTO;
import com.ironshutter.web.interfaces.sign.dto.SignInForm;
import com.ironshutter.web.interfaces.sign.dto.SignUpForm;
import com.ironshutter.web.interfaces.sign.facade.SignServiceFacade;

@Component
public class SignServiceFacadeImpl implements SignServiceFacade{

	@Autowired
	AccountService accountService;
	
	@Override
	public void signup(SignUpForm signUpForm) {
		
		Account newAccount = new Account();
		
		accountService.create(newAccount);
	}

	@Override
	public boolean isOccupiedUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AccountDTO signin(SignInForm signInForm, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean signout(HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSignedin(HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SignedInValue getSign(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

}
