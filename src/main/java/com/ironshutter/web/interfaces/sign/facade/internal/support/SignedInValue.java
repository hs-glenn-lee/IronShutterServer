package com.ironshutter.web.interfaces.sign.facade.internal.support;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ironshutter.web.interfaces.sign.facade.dto.AccountDTO;

public class SignedInValue implements Serializable{
	
	private static final long serialVersionUID = 6776507590445171404L;
	
	private Long id;
	
	private AccountDTO accountDTO;
	
	private LocalDateTime signedInDate;

	public SignedInValue(Long id, AccountDTO accountDTO) {
		this.id = id;
		this.accountDTO = accountDTO;
		this.setSignedInDate(LocalDateTime.now());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
