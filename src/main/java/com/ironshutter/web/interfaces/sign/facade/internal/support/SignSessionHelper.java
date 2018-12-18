package com.ironshutter.web.interfaces.sign.facade.internal.support;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class SignSessionHelper {
	
	private static final String SIGN_SESSION_KEY = "SIGNED_IN_KEY";
	
	public void put(SignedInValue signedInValue, HttpSession session) {
		session.setAttribute(SIGN_SESSION_KEY, signedInValue);
	}
	
	public Optional<SignedInValue> get(HttpSession session) {
		Object v = session.getAttribute(SIGN_SESSION_KEY);
		if(v == null) {
			return Optional.empty();
		}
		return Optional.of((SignedInValue) v);
	}
}
