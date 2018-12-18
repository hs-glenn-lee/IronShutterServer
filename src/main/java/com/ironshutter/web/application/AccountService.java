package com.ironshutter.web.application;

import java.util.Optional;

import com.ironshutter.web.domain.model.account.Account;

public interface AccountService {
	public Account create(String username, String plainPassword, String name, String email);
	public Optional<Account> find(String username, String plainPassword);
	public Optional<Account> findById(Long id);
	public boolean isOccupiedUsername(String username);
}
