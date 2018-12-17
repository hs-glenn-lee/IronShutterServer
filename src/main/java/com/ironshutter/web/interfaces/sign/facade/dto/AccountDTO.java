package com.ironshutter.web.interfaces.sign.facade.dto;

import com.ironshutter.web.domain.model.account.Account;

public class AccountDTO {

	private String username;
	private String name;
	private String email;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public AccountDTO() {}
	public AccountDTO(Account account) {
		this.username = account.getSign().getUsername();
		this.name = account.getUser().getName();
		this.email = account.getUser().getEmail();
	}
	
}
