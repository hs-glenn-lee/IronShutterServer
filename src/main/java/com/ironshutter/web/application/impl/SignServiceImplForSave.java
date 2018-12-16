package com.ironshutter.web.domain.account.sign;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironshutter.web.domain.account.Account;
import com.ironshutter.web.domain.account.AccountService;
import com.ironshutter.web.domain.account.sign.support.SignInSpecification;
import com.ironshutter.web.domain.account.sign.support.SignUpSpecification;
import com.ironshutter.web.domain.account.sign.support.SignedInValue;
import com.ironshutter.web.exceptions.NotSignedInException;

@Service("signService")
public class SignServiceImpl implements SignService{
	
	
	private final static String SIGNED_IN_ATTRIBUTE_KEY = "SIGNED_IN_KEY";
	
	@Autowired
	AccountService accountService;

	@Override
	@Transactional
	public void signup(SignUpSpecification signUpSpecifiaction) {
		/*Account newAccount = new Account(signUpSpecifiaction);
		accountService.createNewAccount(newAccount);*/ // not ddd....
	}

	@Override
	public boolean isOccupiedUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account signin(SignInSpecification signInForm, HttpSession httpSession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean signout(HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSignedin(HttpSession httpSession) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SignedInValue getSign(HttpSession session) throws NotSignedInException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
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
