package com.ironshutter.web.model.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironshutter.web.exceptions.NotSignedInException;
import com.ironshutter.web.model.jpa.entities.Account;
import com.ironshutter.web.model.jpa.entities.AccountSetting;

@Service("signService")

public class SignServiceImpl implements SignService{
	
	private final static String SIGN_KEY = "SIGN";
	
	@Autowired
	AccountService accountService;

	
	@Override
	@Transactional
	public boolean signup(Account account) throws IOException {
		Account newAccount = accountService.createNewAccount(account);
		return true;
	}
	
	
	@Override
	public Account signin(Account account, HttpSession httpSession) {

		Account authenicatedAccount = accountService.authenticate(account.getUsername(), account.getPassword());
		
		if(authenicatedAccount == null)
			return null;
		
		Sign sign = new Sign();
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
	/**
	 * return sign object
	 * there's no stored sign object in current session throw NotSignedInException
	 * */
	@Override
	public Sign getSign(HttpSession session) throws NotSignedInException {
		Sign sign = (Sign) session.getAttribute(SIGN_KEY);
		if(sign == null)
			throw new NotSignedInException();
		return sign;
	}
	
	

}
