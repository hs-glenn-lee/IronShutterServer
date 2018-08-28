package com.ironshutter.web.model.jpa.entities;

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


@Entity
@Table(name="subscription_charge_ratio")
public class SubscriptionChargeRatio implements Serializable{

	private static final long serialVersionUID = -4163953969858437768L;

	@Id
	@Column(name="unit_time")
	private String unit_time;
	
	@Column(name="charge_per_unit")
	private Long charge_per_unit;

	public String getUnit_time() {
		return unit_time;
	}

	public void setUnit_time(String unit_time) {
		this.unit_time = unit_time;
	}

	public Long getCharge_per_unit() {
		return charge_per_unit;
	}

	public void setCharge_per_unit(Long charge_per_unit) {
		this.charge_per_unit = charge_per_unit;
	}

}
