package com.ironshutter.web.model.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ironshutter.web.model.jpa.entities.Account;
import com.ironshutter.web.model.jpa.entities.AccountSetting;

public interface AccountSettingService {

	/**
	 * find AccountSetting by accountId
	 * */
	public AccountSetting findAccountSettingByAccountId(Long accountId);
	
	/**
	 * 
	 * */
	public AccountSetting saveAccountSetting(AccountSetting setting);
	

}
