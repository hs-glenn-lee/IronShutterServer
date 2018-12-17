package com.ironshutter.web.interfaces.sign.facade.internal;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ironshutter.web.application.AccountService;
import com.ironshutter.web.domain.model.account.Account;
import com.ironshutter.web.interfaces.sign.facade.SignServiceFacade;
import com.ironshutter.web.interfaces.sign.facade.dto.AccountDTO;
import com.ironshutter.web.interfaces.sign.facade.dto.SignInForm;
import com.ironshutter.web.interfaces.sign.facade.dto.SignUpForm;

@Component
public class SignServiceFacadeImpl implements SignServiceFacade{

	@Autowired
	AccountService accountService;
	
	@Override
	public void signup(SignUpForm signUpForm) {
		accountService.create(signUpForm.getUsername(), signUpForm.getPassword(), signUpForm.getName(), signUpForm.getEmail());
	}

	@Override
	public boolean isOccupiedUsername(String username) {
		return accountService.isOccupiedUsername(username);
	}

	@Override
	public Optional<AccountDTO> signinAndGetAccountDTO(SignInForm signInForm, HttpSession session) {
		Optional<Account> optAccount = accountService.find(signInForm.getUsername(), signInForm.getPassword());
		if(optAccount.isPresent()) {
			
			//TODO do something with session
			
			AccountDTO signedInPublicAccountDTO = new AccountDTO(optAccount.get());
			return Optional.of(signedInPublicAccountDTO);
		}else {
			return Optional.empty();
		}
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
	public Optional<AccountDTO> getSign(HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}

}
