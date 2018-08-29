package com.ironshutter.web.model.service.subscription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironshutter.web.model.jpa.entities.SubscriptionChargeRatio;
import com.ironshutter.web.model.jpa.repos.SubscriptionChargeRatioRepo;

@Service("subscriptionChargeRatio")
public class SubscriptionChargeRatioImpl implements SubscriptionChargeRatioService{

	@Autowired
	SubscriptionChargeRatioRepo subcriptionChargeRationRepo;
	
	@Override
	public List<SubscriptionChargeRatio> getAll() {
		List<SubscriptionChargeRatio> ret = subcriptionChargeRationRepo.findAll();
		return ret;
	}

}
