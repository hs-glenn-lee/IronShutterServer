package com.ironshutter.web.domain.model.subscription;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ironshutter.web.domain.model.account.Account;
import com.ironshutter.web.interfaces.shared.AppAuth;

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
