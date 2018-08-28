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
	private String unitTime;
	
	@Column(name="charge_per_unit")
	private Long chargePerUnit;

	public String getUnit_time() {
		return unitTime;
	}

	
	public String getUnitTime() {
		return unitTime;
	}

	public void setUnitTime(String unitTime) {
		this.unitTime = unitTime;
	}

	public Long getChargePerUnit() {
		return chargePerUnit;
	}

	public void setChargePerUnit(Long chargePerUnit) {
		this.chargePerUnit = chargePerUnit;
	}


	public String toString() {
		String ret = "{" +
				"unitTime: " + unitTime + ", " +
				"chargePerUnit:" + chargePerUnit + ", "+ 
				"}";
		return ret;
	}
}
