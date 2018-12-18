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
import com.ironshutter.web.interfaces.sign.facade.internal.support.SignSessionHelper;
import com.ironshutter.web.interfaces.sign.facade.internal.support.SignedInValue;

@Component
public class SignServiceFacadeImpl implements SignServiceFacade{

	@Autowired
	AccountService accountService;
	
	@Autowired
	SignSessionHelper signSessionHelper;
	
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
			Account account = optAccount.get();
			AccountDTO accountDTO = new AccountDTO(account); 
			
			SignedInValue sessionValue = new SignedInValue(account.getId(), accountDTO);
			signSessionHelper.put(sessionValue, session);
			
			return Optional.of(accountDTO);
		}else {
			return Optional.empty();
		}
	}

	@Override
	public boolean signout(HttpSession session) {
		session.invalidate();
		return false;
	}

	@Override
	public boolean isSignedin(HttpSession session) {
		if(signSessionHelper.get(session) == null) {
			return true;
		}
		return false;
	}

	@Override
	public Optional<SignedInValue> getSignedInValue(HttpSession session) {
		return signSessionHelper.get(session);
	}

}
