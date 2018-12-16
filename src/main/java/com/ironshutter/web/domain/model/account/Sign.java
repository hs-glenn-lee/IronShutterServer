package com.ironshutter.web.domain.model.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="signs")
public class Sign {
	
	@Id
	@Column(name="account_id")
	private Long accountId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="hashed_password")
	private String hashedPassword;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
	private Account account;
	
}
