package com.ironshutter.web.model.jpa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ironshutter.web.model.jpa.entities.SubscriptionChargeRatio;

public interface SubscriptionChargeRatioRepo extends JpaRepository<SubscriptionChargeRatio, String>{
	
	@Query("SELECT cr FROM SubscriptionChargeRatio cr  "
			+ " WHERE cr.unitTime = :unitTime ")
	public SubscriptionChargeRatio findByUnitTime(@Param("unitTime")String unitTime);
}
