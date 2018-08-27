package com.ironshutter.web.model.service.account;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironshutter.web.model.jpa.entities.Account;
import com.ironshutter.web.model.jpa.entities.AccountSetting;
import com.ironshutter.web.model.jpa.repos.AccountRepo;
import com.ironshutter.web.model.jpa.repos.AccountSettingRepo;


@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	AccountSettingRepo accountSettingRepo;
	
	@Autowired
	EntityManager em;

	@Transactional
	@Override
	public Account createNewAccount(Account newAccount) throws IllegalStateException{
		validateNewAccount(newAccount);
		
		 accountRepo.saveAndFlush(newAccount);
		
		AccountSetting actSet = new AccountSetting();
		actSet.setAccount(newAccount);
		actSet = accountSettingRepo.saveAndFlush(actSet);
		newAccount.setAccountSetting(actSet);

		return newAccount;
	}
	
	/**
	 * validate account instance. if instance is invalidate throw IllegalStateException.
	 * */
	private void validateNewAccount(Account newAccount) throws IllegalStateException{
		if(!isUniqueNewUsername(newAccount.getUsername())) {
			throw new IllegalStateException("이미 사용중인 사용자명입니다.");
		}
	}
	
	@Transactional
	@Override
	public Account authenticate(String username, String password) {
		List<Account> finded = accountRepo.findByUsernameAndPassword(username, password);
		if(finded.isEmpty()) {
			return null;
		}else {
			return finded.get(0);
		}
	}
	
	@Override
	public boolean isUniqueNewUsername(String username) {
		Account findByName = accountRepo.findByUsername(username);
		if(findByName != null) {
			return false;
		}else {
			return true;
		}
	}


	@Override
	public AccountSetting getPublicAccountSetting(String username) {
		if(username == null) {
			throw new IllegalStateException("Username is null.");
		}
		
		AccountSetting ret = accountSettingRepo.findByAccountUsername(username);
		if(ret == null) {
			throw new IllegalStateException("Account not found.");
		}
		
		ret.getAccount().setPassword(null);
		return ret;
	}
	
	
	

}
