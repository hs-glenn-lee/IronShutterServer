package com.ironshutter.web.application.account.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironshutter.web.application.account.AccountService;
import com.ironshutter.web.application.support.SHA256Encryptor;
import com.ironshutter.web.domain.model.account.Account;
import com.ironshutter.web.domain.model.account.AccountRepository;
import com.ironshutter.web.domain.model.account.Sign;
import com.ironshutter.web.domain.model.account.SignRepository;
import com.ironshutter.web.domain.model.account.User;
import com.ironshutter.web.support.UUIDUtil;

@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	SignRepository signRepository;
	
	@Override
	public Account create(String username, String plainPassword, String name, String email) {
		String accountId = UUIDUtil.newUUID();
		Sign sign = new Sign(accountId, username, SHA256Encryptor.encrypt(plainPassword));
		User user = new User(accountId, name, email);
		Account newAccount = new Account(accountId);
		newAccount.setSign(sign);
		newAccount.setUser(user);
		//TODO validate entities
		
		accountRepository.save(newAccount);

		return newAccount;
	}

	@Override
	public Optional<Account> find(String username, String plainPassword) {
		return accountRepository.findByUsernameAndHashedPassword(username, SHA256Encryptor.encrypt(plainPassword));
	}

	@Override
	public boolean isOccupiedUsername(String username) {
		Optional<Sign> sign = signRepository.findByUsername(username);
		return sign.isPresent();
	}

	@Override
	public Optional<Account> findById(String id) {
		return accountRepository.findById(id);
	}

	@Override
	public Account updatePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}

}
