package com.ironshutter.web.interfaces.sign.facade.internal.support;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ironshutter.web.interfaces.sign.facade.dto.AccountDTO;

public class SignedInValue implements Serializable{
	
	private static final long serialVersionUID = 6776507590445171404L;
	
	private String id;
	
	private AccountDTO accountDTO;
	
	private LocalDateTime signedInDate;

	public SignedInValue(String id, AccountDTO accountDTO) {
		this.id = id;
		this.accountDTO = accountDTO;
		this.setSignedInDate(LocalDateTime.now());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AccountDTO getAccountDTO() {
		return accountDTO;
	}

	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}

	public LocalDateTime getSignedInDate() {
		return signedInDate;
	}

	public void setSignedInDate(LocalDateTime signedInDate) {
		this.signedInDate = signedInDate;
	}
	
}
