package com.ironshutter.web.model.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironshutter.web.model.jpa.entities.Account;
import com.ironshutter.web.model.jpa.entities.AccountSetting;
import com.ironshutter.web.model.jpa.repos.AccountRepo;
import com.ironshutter.web.model.jpa.repos.AccountSettingRepo;


@Service("accountSettingService")
@Transactional
public class AccountSettingServiceImpl implements AccountSettingService{
	
	@Autowired
	AccountSettingRepo accountSettingRepo;
	
	@Autowired
	AccountRepo accountRepo;
	

	@Override
	public AccountSetting findAccountSettingByAccountId(Long accountId) {
		/*Optional<Account> account = accountRepo.findById(accountId);
		if(account.isPresent()) {
			return account.get();
		}else {
			return null;
		}*/
		
		return null;
	}

	@Override
	public AccountSetting saveAccountSetting(AccountSetting setting) {
		return accountSettingRepo.save(setting);
	}




}
