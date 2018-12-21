package com.ironshutter.web.application;

import java.util.Optional;

import com.ironshutter.web.domain.model.account.Account;

public interface AccountService {
	public boolean isOccupiedUsername(String username);
	public Account create(String username, String plainPassword, String name, String email);
	public Account updatePassword(String oldPassword, String newPassword);
	
	public Optional<Account> find(String username, String plainPassword);
	public Optional<Account> findById(String id);
	
}
