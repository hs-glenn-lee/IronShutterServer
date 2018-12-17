package com.ironshutter.web.domain.model.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.ironshutter.web.interfaces.sign.facade.dto.SignUpForm;


@Entity
@Table(name="accounts")
public class Account implements Serializable{

	private static final long serialVersionUID = -4163953969858437768L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="is_retired")
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isRetired = false;
	
	@OneToOne(mappedBy="account", fetch = FetchType.LAZY)
	private User user;
	
	@OneToOne(mappedBy="account", fetch = FetchType.LAZY)
	private Sign sign;

	public Account() {}
	public Account(Sign sign, User user) {
		this.sign = sign;
		this.user = user;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getId() {
		return id;
	}
	public Boolean getIsRetired() {
		return isRetired;
	}
	public User getUser() {
		return user;
	}
	public Sign getSign() {
		return sign;
	}
	
}
