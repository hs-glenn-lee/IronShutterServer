package com.ironshutter.web.model.service.subscription;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ironshutter.web.controllers.rest.responses.AppAuth;
import com.ironshutter.web.model.jpa.entities.Account;
import com.ironshutter.web.model.jpa.entities.Subscription;

public interface SubscriptionService {
	public Subscription request(Subscription subscription);
	public Subscription cancel(Subscription subscription);
	public Subscription permit(Subscription subscription);
	public Subscription activate(Subscription subscription);
	public Subscription expire(Subscription subscription);
	
	public List<Subscription> getSubscriptionsOf(Account account);
	public List<Subscription> getActivatedSubscriptionsOf(Account account);
	public List<Subscription> getRequestedSubscriptionsOf(Account account);
	
	public List<Subscription> getSubscriptionOverviewOf(Account account);
	
	public AppAuth authenticateForApp(String username);
	
	public Page<Subscription> getRequestedSubscription(Pageable page);
	
}
