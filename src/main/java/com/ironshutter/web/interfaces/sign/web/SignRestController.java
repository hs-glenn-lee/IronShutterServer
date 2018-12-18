package com.ironshutter.web.interfaces.sign.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ironshutter.web.domain.model.account.Account;
import com.ironshutter.web.interfaces.exceptions.NotSignedInException;
import com.ironshutter.web.interfaces.shared.GenericResponse;
import com.ironshutter.web.interfaces.sign.facade.SignServiceFacade;
import com.ironshutter.web.interfaces.sign.facade.dto.AccountDTO;
import com.ironshutter.web.interfaces.sign.facade.dto.SignInForm;
import com.ironshutter.web.interfaces.sign.facade.dto.SignUpForm;
import com.ironshutter.web.interfaces.sign.facade.internal.support.SignedInValue;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SignRestController {
	
	@Autowired
	SignServiceFacade signServiceFacade;
	
	@RequestMapping(value="/sign-up", method=RequestMethod.PUT)
	public @ResponseBody GenericResponse<?> signup(@RequestBody SignUpForm signUpForm) throws IOException {
		signServiceFacade.signup(signUpForm);
		return new GenericResponse<Object>();
	}
	
	@RequestMapping(value="/isOccupiedUsername", method=RequestMethod.POST)
	public HashMap<String, Boolean> isUniqueUsername(@RequestBody HashMap<String, Object> jsonMap) {
		boolean isOccupiedUsername = signServiceFacade.isOccupiedUsername((String)jsonMap.get("username"));
		HashMap<String, Boolean> responseMap = new HashMap<>();
		responseMap.put("isOccupiedUsername", isOccupiedUsername);
		return responseMap;
	}
	
	@RequestMapping(value="/sign-in", method=RequestMethod.POST)
	public GenericResponse<?> signin(@RequestBody SignInForm signInForm, HttpServletRequest req) {
		Optional<AccountDTO> optAccountDTO = signServiceFacade.signinAndGetAccountDTO(signInForm, req.getSession());
		if(optAccountDTO.isPresent()) {
			return new GenericResponse<AccountDTO>(optAccountDTO.get());
		}else {
			return GenericResponse.getFailInstance("일치하는 사용자 정보가 없습니다.");
		}
	}

	@RequestMapping(value="/myAccount", method=RequestMethod.GET)
	public GenericResponse<?> getAccountDTO(HttpServletRequest req) throws NotSignedInException {
		Optional<SignedInValue> optSignedInValue = signServiceFacade.getSignedInValue(req.getSession());
		
		if(optSignedInValue.isPresent()) {
			return new GenericResponse<AccountDTO>(optSignedInValue.get().getAccountDTO());
		}else {
			return GenericResponse.getFailInstance("로그인이 필요합니다.");
		}
	}
	
	@RequestMapping(value="/sign-out", method=RequestMethod.POST)
	public GenericResponse<?> signout(HttpServletRequest req) {
		req.getSession().invalidate();
		GenericResponse<Object> res = new GenericResponse<Object>();
		return res;
	}
	
}
