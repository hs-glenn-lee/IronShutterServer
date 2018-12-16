package com.ironshutter.web.domain.account;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.ironshutter.web.domain.account.sign.Sign;
import com.ironshutter.web.domain.account.user.User;
import com.ironshutter.web.domain.subscription.Subscription;


@Entity
@Table(name="accounts")
public class Account implements Serializable{

	private static final long serialVersionUID = -4163953969858437768L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="is_valid")
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isValid = false;
	
	@OneToOne(mappedBy="account", fetch = FetchType.LAZY)
	private User user;
	
	@OneToOne(mappedBy="account", fetch = FetchType.LAZY)
	private Sign sign;

	public Account() {}


}
