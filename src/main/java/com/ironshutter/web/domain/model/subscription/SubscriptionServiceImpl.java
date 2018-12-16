package com.ironshutter.web.domain.model.subscription;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironshutter.web.domain.model.account.Account;
import com.ironshutter.web.infrastructure.persistence.springDataJpa.AccountRepositorySpringJpa;
import com.ironshutter.web.interfaces.shared.AppAuth;
import com.ironshutter.web.support.UUIDUtil;

public class SubscriptionServiceImpl{
	/*
	Logger logger = LoggerFactory.getLogger(SubscriptionService.class);
	
	@Autowired
	SubscriptionRepo subscriptionRepo;
	
	@Autowired
	SubscriptionChargeRatioRepo subscriptionChargeRatioRepo;
	
	@Autowired
	AccountRepo accountRepo;

	@Transactional
	@Override
	public Subscription request(Subscription subscription) {
		
		
		validateRequestedSubscription(subscription);
		
		//find consistent me and set as subscriber
		subscription.setId(UUIDUtil.newUUID());
		Optional<Account> me = accountRepo.findById(subscription.getSubscriber().getId());
		subscription.setSubscriber(me.get());
		subscription.setState(Subscription.State.REQUESTED);
		subscription.setRequestedAt(new Date());
		calculateAndSetChargeAmount(subscription);
		
		subscriptionRepo.save(subscription);
		return subscription;
		
		return null;
		
	}
	private void validateRequestedSubscription(Subscription subs) {
		if(subs.getPeriodAmount() == null || subs.getPeriodAmount() < 0) {
			throw new IllegalStateException("기간이 입력되지 않았습니다.");
		}
		if(subs.getPeriodUnit() == null || "".equals(subs.getPeriodUnit())) {
			throw new IllegalStateException("기간단위가 입력되지 않았습니다.");
		}
		if(subs.getSubscriber() == null) {
			throw new IllegalStateException("구독 신청자가 입력되지 않았습니다.");
		}
		
		List<Subscription> activated = subscriptionRepo.findActivatedSubscriptionOf(subs.getSubscriber());
		List<Subscription> permitted = subscriptionRepo.findActivatedSubscriptionOf(subs.getSubscriber());
		List<Subscription> requested = subscriptionRepo.findRequestedSubscriptionOf(subs.getSubscriber());
		
		if(!requested.isEmpty()) {
			throw new IllegalStateException("이미 신청된 구독이 있습니다.");
		}
		
		if(activated.size() > 0 && permitted.size() > 0) {//활성화된 구독 있음, 승인된 구독 이미 있음
			throw new IllegalStateException("예약 구독 신청은 1개만 가능합니다. 이미 활성화 대기중인 구독이 있습니다.");
		}
		
	}
	private void calculateAndSetChargeAmount(Subscription subs) {
		SubscriptionChargeRatio ratio = subscriptionChargeRatioRepo.findByUnitTime(subs.getPeriodUnit());
		Long chargeamount = subs.getPeriodAmount() * ratio.getChargePerUnit();
		subs.setChargeAmount(chargeamount);
	}
	
	@Transactional
	@Override
	public Subscription cancel(Subscription subscription) {
		Optional<Subscription> opt = subscriptionRepo.findById(subscription.getId());
		validateCancelingSubscription(opt);
		
		Subscription target = opt.get();
		target.setState(Subscription.State.CANCELED);
		target.setCanceledAt(new Date());
		subscriptionRepo.save(target);
		return target;
	}
	public void validateCancelingSubscription(Optional<Subscription> opt) {
		if(!opt.isPresent())
			throw new IllegalStateException("해당하는 구독이 없습니다.");
		Subscription target = opt.get();
		if(!target.getState().equals(Subscription.State.REQUESTED)) {
			throw new IllegalStateException("취소할 수 없는 상태의 구독입니다.");
		}
	}
	
	@Transactional
	@Override
	public Subscription permit(Subscription subscription) {
		Optional<Subscription> opt = subscriptionRepo.findById(subscription.getId());
		validatePermittingSubscription(opt);
		
		Subscription target = opt.get();
		target.setState(Subscription.State.PERMITTED);
		target.setPermittedAt(new Date());
		subscriptionRepo.save(target);
		return target;
	}
	public void validatePermittingSubscription(Optional<Subscription> opt) {
		if(!opt.isPresent())
			throw new IllegalStateException("해당하는 구독이 없습니다.");
		Subscription target = opt.get();
		if(!target.getState().equals(Subscription.State.REQUESTED)) {
			throw new IllegalStateException("승인할 수 없는 상태의 구독입니다.");
		}
	}
	
	@Transactional
	@Override
	public Subscription activate(Subscription subscription) {
		Optional<Subscription> opt = subscriptionRepo.findById(subscription.getId());
		validateActivatingSubscription(opt);
		
		Subscription target = opt.get();
		target.setState(Subscription.State.ACTIVATED);
		target.setActivatedAt(new Date());
		target.setExpireAt(calculateExpireDate(target, target.getActivatedAt()));
		subscriptionRepo.save(target);
		return target;
	}
	private Date calculateExpireDate(Subscription subscription, Date start) {
		Long amount = subscription.getPeriodAmount();
		String unit = subscription.getPeriodUnit();
		Date expireAt = DateUtils.addMonths(start, amount.intValue());
		DateUtils.addDays(expireAt, 1);
		DateUtils.addSeconds(expireAt, -1);
		return expireAt;
	}
	
	private void validateActivatingSubscription(Optional<Subscription> opt) {
		if(!opt.isPresent())
			throw new IllegalStateException("해당하는 구독이 없습니다.");
		Subscription target = opt.get();
		if(!target.getState().equals(Subscription.State.PERMITTED)) {
			throw new IllegalStateException("활성화할 수 없는 상태의 구독입니다.");
		}
		
		//TODO 이미 활성화 되어있는 구독이 있는지 확인
	}

	@Override
	public Subscription expire(Subscription subscription) {
		Optional<Subscription> opt = subscriptionRepo.findById(subscription.getId());
		validateActivatingSubscription(opt);
		
		Subscription target = opt.get();
		target.setState(Subscription.State.EXPIRED);
		subscriptionRepo.save(target);
		return null;
	}
	public void validateExpiringSubscription(Optional<Subscription> opt) {
		if(!opt.isPresent())
			throw new IllegalStateException("해당하는 구독이 없습니다.");
		Subscription target = opt.get();
		if(!target.getState().equals(Subscription.State.ACTIVATED)) {
			throw new IllegalStateException("만료시킬 수 없는 상태의 구독입니다.");
		}
		Date now = new Date();
		if(!now.after(target.getExpireAt())) {
			throw new IllegalStateException("만료일이 지나지 않았습니다.");
		}
	}


	@Override
	public List<Subscription> getSubscriptionsOf(Account account) {
		List<Subscription> ret = subscriptionRepo.findSubscriptionOf(account);
		return ret;
	}

	@Override
	public List<Subscription> getActivatedSubscriptionsOf(Account account) {
		List<Subscription> ret = subscriptionRepo.findActivatedSubscriptionOf(account);
		return ret;
	}

	@Override
	public List<Subscription> getRequestedSubscriptionsOf(Account account) {
		List<Subscription> ret = subscriptionRepo.findRequestedSubscriptionOf(account);
		return ret;
	}
	
	@Override
	public List<Subscription> getSubscriptionOverviewOf(Account account) {
		List<Subscription> importants = subscriptionRepo.findImportantSubscriptionsOf(account);
		return importants;
	}
	
	
	@Transactional
	@Override
	public AppAuth authenticateForApp(String username) {
		Account account = accountRepo.findByUsername(username);
		if(account == null) { throw new IllegalStateException("해당 사용자를 찾을 수 없습니다."); }
		
		List<Subscription> importants = subscriptionRepo.findImportantSubscriptionsOf(account);
		List<Subscription> activated = new ArrayList<Subscription>();
		List<Subscription> permitted = new ArrayList<Subscription>();

		for(Subscription sub : importants) {
			switch(sub.getState()) {
			case Subscription.State.ACTIVATED:
				if(activated.size() > 1) { }
				activated.add(sub);
				break;
			case Subscription.State.PERMITTED:
				permitted.add(sub);
				break;
			}
		}
		
		if(activated.size() > 1) { logger.error("Something wrong ..activated must be one or zero."); }
		if(permitted.size() > 1) { logger.error("Something wrong .. permitted must be one or zero."); }
		
		AppAuth ret = null;
		
		expireIfSubcriptionIsOutOfDate(activated); // 배치로 처리하는게 맞는것 같다.
		
		if(activated.isEmpty()) {			
			if(permitted.isEmpty()) {
				return new AppAuth(AppAuth.Result.EXPIRED);
			}else {
				ret = new AppAuth(AppAuth.Result.ACTIVATE_NEW_SUBS);
				return ret;
			}
		}else {
			ret = new AppAuth(AppAuth.Result.AUTHORIZED);
			ret.setActivated(activated.get(0));
			return ret;
		}
	}
	
	private void expireIfSubcriptionIsOutOfDate(List<Subscription> activated) {
		Date now = new Date();
		for(int i = 0; i < activated.size(); i++) {
			Subscription sub = activated.get(i);
			Date expireAt = sub.getExpireAt();
			if(now.after(expireAt)) {
				activated.remove(i);
				this.expire(sub);
			}
		}
	}
	
	private Subscription activatePermittedSubscription(List<Subscription> permitted) {
		Subscription nextAct = permitted.get(0);
		return this.activate(nextAct);
	}
	@Override
	public Page<Subscription> getRequestedSubscription(Pageable page) {
		return subscriptionRepo.findRequestedSubscriptionByPage(page);
	}
*/
}
