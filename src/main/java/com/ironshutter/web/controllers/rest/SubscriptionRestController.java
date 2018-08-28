package com.ironshutter.web.controllers.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ironshutter.web.exceptions.NotSignedInException;
import com.ironshutter.web.model.jpa.entities.Account;
import com.ironshutter.web.model.jpa.entities.Subscription;
import com.ironshutter.web.model.service.sign.SignService;
import com.ironshutter.web.model.service.subscription.SubscriptionService;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SubscriptionRestController {

	@Autowired
	SignService signService;
	
	@Autowired
	SubscriptionService subscriptionService;
	
	@RequestMapping(value="/subscription/request", method=RequestMethod.POST)
	public @ResponseBody Subscription requestSubscription(@RequestBody Subscription newSubscription, HttpServletRequest req) throws IOException, NotSignedInException {
		Account me = signService.getSign(req.getSession()).getAccount();
		newSubscription.setSubscriber(me);
		Subscription requested = subscriptionService.request(newSubscription);
		return requested;
	}
	
	@RequestMapping(value="/subscription/permit", method=RequestMethod.POST)
	public @ResponseBody Subscription permitSubscription(@RequestBody Subscription permittingSubscription, HttpServletRequest req) throws IOException, NotSignedInException {
		// TODO add authenticate
		Subscription requested = subscriptionService.permit(permittingSubscription);
		return requested;
	}
	
	@RequestMapping(value="/subscription/authenticate", method=RequestMethod.POST)
	public String appAuthenticate(HttpServletRequest req) {
		
		return null;
	}
	
	
	
	
	@RequestMapping(value="/subscription/requested", method=RequestMethod.GET)
	public @ResponseBody List<Subscription> getRequestedSubscriptions(HttpServletRequest req) throws IOException, NotSignedInException {
		Account me = signService.getSign(req.getSession()).getAccount();
		List<Subscription> requested = subscriptionService.getRequestedSubscriptionsOf(me);
		return requested;
	}
	
	@RequestMapping(value="/subscription/me", method=RequestMethod.GET)
	public @ResponseBody List<Subscription> signup(HttpServletRequest req) throws IOException, NotSignedInException {
		Account me = signService.getSign(req.getSession()).getAccount();
		List<Subscription> mines = subscriptionService.getSubscriptionsOf(me);
		return mines;
	}
	
	@RequestMapping(value="/subscription/overview", method=RequestMethod.GET)
	public @ResponseBody List<Subscription> isActiavated(HttpServletRequest req) throws IOException, NotSignedInException {
		Account me = signService.getSign(req.getSession()).getAccount();
		List<Subscription> mines = subscriptionService.getSubscriptionOverviewOf(me);
		return mines;
	}
	

}
