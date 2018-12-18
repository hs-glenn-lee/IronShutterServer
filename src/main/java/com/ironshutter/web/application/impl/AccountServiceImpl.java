package com.ironshutter.web.application.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironshutter.web.application.AccountService;
import com.ironshutter.web.application.support.SHA256Encryptor;
import com.ironshutter.web.domain.model.account.Account;
import com.ironshutter.web.domain.model.account.AccountRepository;
import com.ironshutter.web.domain.model.account.Sign;
import com.ironshutter.web.domain.model.account.SignRepository;
import com.ironshutter.web.domain.model.account.User;

@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	SignRepository signRepository;

	@Override
	@Transactional
	public Account create(String username, String plainPassword, String name, String email) {
		Sign sign = new Sign(username, SHA256Encryptor.encrypt(plainPassword));
		User user = new User(name, email);
		Account newAccount = new Account(sign, user);
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
	public Optional<Account> findById(Long id) {
		return accountRepository.findById(id);
	}

}
