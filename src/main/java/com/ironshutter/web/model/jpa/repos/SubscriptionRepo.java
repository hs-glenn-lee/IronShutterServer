package com.ironshutter.web.model.jpa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ironshutter.web.model.jpa.entities.Account;
import com.ironshutter.web.model.jpa.entities.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, String>{
	
	@Query("SELECT subs FROM Subscription subs "
			+ "WHERE subs.subscriber = :account ")
	List<Subscription> findSubscriptionOf(Account account);
	
	@Query("SELECT subs FROM Subscription subs "
			+ "WHERE subs.subscriber = :account "
			+ "AND subs.state = '" + Subscription.State.ACTIVATED +"' ")
	List<Subscription> findActivatedSubscriptionOf(Account account);
	
	@Query("SELECT subs FROM Subscription subs "
			+ "WHERE subs.subscriber = :account "
			+ "AND subs.state = '" + Subscription.State.REQUESTED +"' ")
	List<Subscription> findRequestedSubscriptionOf(Account account);
	
	@Query("SELECT subs FROM Subscription subs "
			+ "WHERE subs.subscriber = :account "
			+ "AND subs.state = '" + Subscription.State.PERMITTED +"' ")
	List<Subscription> findPermittedSubscriptionsOf(Account account);
	
	@Query("SELECT subs FROM Subscription subs "
			+ "WHERE subs.subscriber = :account "
			+ "AND subs.state = '" + Subscription.State.ACTIVATED +"' "
			+ "AND subs.state = '" + Subscription.State.REQUESTED +"' "
			+ "AND subs.state = '" + Subscription.State.PERMITTED +"' ")
	List<Subscription> findImportantSubscriptionsOf(Account account);
	
}
