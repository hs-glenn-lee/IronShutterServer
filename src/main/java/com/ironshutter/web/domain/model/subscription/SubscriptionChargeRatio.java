package com.ironshutter.web.domain.model.subscription;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="subscription_charge_ratio")
public class SubscriptionChargeRatio implements Serializable{

	private static final long serialVersionUID = -4163953969858437768L;

	@Id
	@Column(name="unit_time")
	private String unitTime;
	
	@Column(name="charge_per_unit")
	private Long chargePerUnit;


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
