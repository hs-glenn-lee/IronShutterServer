package com.ironshutter.web.domain.model.account;

import com.ironshutter.web.domain.shared.AbstractSpecification;
import com.ironshutter.web.domain.shared.ValueObject;
import com.ironshutter.web.interfaces.sign.facade.dto.SignUpForm;

public class SignUpSpecification extends AbstractSpecification<SignUpForm> implements ValueObject<SignUpSpecification> {
	
	private String username;
	private String password;
	private String email;
	
	public SignUpSpecification(String username, String password, String email) {
		
	}

	@Override
	public boolean sameValueAs(final SignUpSpecification other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSatisfiedBy(final SignUpForm t) {
		// TODO Auto-generated method stub
		return false;
	}
}
