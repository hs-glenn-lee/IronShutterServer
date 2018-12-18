package com.ironshutter.web.application.impl;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironshutter.web.application.AccountService;
import com.ironshutter.web.domain.model.account.Account;
import com.ironshutter.web.interfaces.exceptions.NotSignedInException;
import com.ironshutter.web.interfaces.sign.facade.SignServiceFacade;
import com.ironshutter.web.interfaces.sign.facade.dto.SignInForm;
import com.ironshutter.web.interfaces.sign.facade.dto.SignUpForm;
import com.ironshutter.web.interfaces.sign.facade.internal.support.SignedInValue;

@Service("signService")
public class SignServiceImplForSave {
	
	
	private final static String SIGNED_IN_ATTRIBUTE_KEY = "SIGNED_IN_KEY";
	

	
/*
	
	@Override
	@Transactional
	public Account signup(SignUpForm signUpForm) {
		Account newAccount = accountService.createNewAccount(account);
		return newAccount;
	}
	
	
	@Override
	public Account signin(Account account, HttpSession httpSession) {
		Account authenicatedAccount = accountService.authenticate(account.getUsername(), account.getPassword());
		
		if(authenicatedAccount == null)
			return null;
		
		SessionSign sign = new SessionSign();
		sign.setAccount(authenicatedAccount);
		httpSession.setAttribute(SIGN_KEY, sign);
		
		return authenicatedAccount;
	}

	@Override
	public boolean signout(HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSignedin(HttpSession httpSession) {
		if(httpSession.getAttribute(SIGN_KEY) == null) {
			return false;
		}else {
			return true;
		}
	}
	*//**
	 * return sign object
	 * there's no stored sign object in current session throw NotSignedInException
	 * *//*
	@Override
	public SessionSign getSign(HttpSession session) throws NotSignedInException {
		SessionSign sign = (SessionSign) session.getAttribute(SIGN_KEY);
		if(sign == null)
			throw new NotSignedInException();
		return sign;
	}
	*/
	

	
}
