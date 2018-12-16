package com.ironshutter.web.domain.account.sign;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ironshutter.web.domain.account.Account;
import com.ironshutter.web.domain.account.support.SignInForm;
import com.ironshutter.web.domain.account.support.SignUpForm;
import com.ironshutter.web.exceptions.NotSignedInException;

@Service("signService")
public class SignServiceImpl implements SignService{
	
	
	@Override
	public Account signup(SignUpForm signUpForm) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOccupiedUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account signin(SignInForm signInForm, HttpSession httpSession) {
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
	public SessionSign getSign(HttpSession session) throws NotSignedInException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
