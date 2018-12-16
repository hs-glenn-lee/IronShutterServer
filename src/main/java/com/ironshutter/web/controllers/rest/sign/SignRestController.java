package com.ironshutter.web.controllers.rest.sign;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ironshutter.web.controllers.rest.support.GenericResponse;
import com.ironshutter.web.domain.account.Account;
import com.ironshutter.web.domain.account.AccountService;
import com.ironshutter.web.domain.account.sign.SessionSign;
import com.ironshutter.web.domain.account.sign.SignService;
import com.ironshutter.web.domain.account.support.SignInForm;
import com.ironshutter.web.domain.account.support.SignUpForm;
import com.ironshutter.web.exceptions.NotSignedInException;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SignRestController {
	
	@Autowired
	SignService signService;
	
	@RequestMapping(value="/sign-up", method=RequestMethod.PUT)
	public @ResponseBody GenericResponse<?> signup(@RequestBody SignUpForm signUpForm) throws IOException {
		signService.signup(signUpForm);
		return new GenericResponse<Object>();
	}
	
	@RequestMapping(value="/isOccupiedUsername", method=RequestMethod.POST)
	public HashMap<String, Boolean> isUniqueUsername(@RequestBody HashMap<String, Object> jsonMap) {
		boolean isOccupiedUsername = signService.isOccupiedUsername((String)jsonMap.get("username"));
		HashMap<String, Boolean> ret = new HashMap<>();
		ret.put("isOccupiedUsername", isOccupiedUsername);
		return ret;
	}
	
	@RequestMapping(value="/sign-in", method=RequestMethod.POST)
	public GenericResponse<?> signin(@RequestBody SignInForm signInForm, HttpServletRequest req) {
		Account account = signService.signin(signInForm, req.getSession());
		if(account == null) {
			return GenericResponse.getFail("일치하는 사용자 정보가 없습니다.");
		}else {
			setPrivatePropertyNull(account);
			GenericResponse<Account> gr = new GenericResponse<Account>();
			gr.setData(account);
			return gr;
		}
	}

	@RequestMapping(value="/myAccount", method=RequestMethod.GET)
	public Account getMyAccount(HttpServletRequest req) throws NotSignedInException {
		SessionSign sign = signService.getSign(req.getSession());
		Account mine = sign.getAccount();

		return mine;
	}
	
	@RequestMapping(value="/sign-out", method=RequestMethod.POST)
	public GenericResponse<?> signout(HttpServletRequest req) {
		req.getSession().invalidate();
		GenericResponse<Object> res = new GenericResponse<Object>();
		return res;
	}
	
	private void setPrivatePropertyNull(Account account) {
		if(account == null)
			return;
	}

}
