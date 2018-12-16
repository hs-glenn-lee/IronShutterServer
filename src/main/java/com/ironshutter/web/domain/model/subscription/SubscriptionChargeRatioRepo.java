package com.ironshutter.web.domain.model.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscriptionChargeRatioRepo extends JpaRepository<SubscriptionChargeRatio, String>{
	/*
	@Query("SELECT cr FROM SubscriptionChargeRatio cr  "
			+ " WHERE cr.unitTime = :unitTime ")
	public SubscriptionChargeRatio findByUnitTime(@Param("unitTime")String unitTime);
	
	*/
}
