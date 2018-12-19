package com.ironshutter.web.domain.model.account;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name="accounts")
public class Account implements Serializable{

	private static final long serialVersionUID = -4163953969858437768L;

	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="is_retired")
	@Type(type="org.hibernate.type.NumericBooleanType")
	private Boolean isRetired = false;
	
	@OneToOne(mappedBy="account", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private User user;
	
	@OneToOne(mappedBy="account", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Sign sign;
	
	public Account() {}
	public Account(String id) {
		this.id = id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public void setSign(Sign sign) {
		if(this.sign != null) {
			this.sign.setAccount(null);
		}
		
		this.sign = sign;
		if(sign == null)
			return;
		
		if(sign.getAccount() != this) {
			sign.setAccount(this);
		}

	}
	
	/*
	public void setUser(User user) {
		if(this.user != null) {
			this.user.setAccount(null);
		}
		
		this.user = user;
		
		if(user == null)
			return;
		
		if(user.getAccount() != this) {
			user.setAccount(this);
		}
		
	}
	*/
	public void setUser(User user) {
		this.user = user;
	}
	
}
