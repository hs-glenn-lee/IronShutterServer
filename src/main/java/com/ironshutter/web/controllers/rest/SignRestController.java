package com.ironshutter.web.controllers.rest;

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

import com.ironshutter.web.controllers.rest.responses.GenericResponse;
import com.ironshutter.web.exceptions.NotSignedInException;
import com.ironshutter.web.model.jpa.entities.Account;
import com.ironshutter.web.model.service.account.AccountService;
import com.ironshutter.web.model.service.sign.Sign;
import com.ironshutter.web.model.service.sign.SignService;

@RestController
@RequestMapping(value="/api", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SignRestController {
	
	@Autowired
	SignService signService;
	
	@Autowired
	AccountService accountSerivce;
	
	@RequestMapping(value="/sign-up", method=RequestMethod.PUT)
	public @ResponseBody GenericResponse<?> signup(@RequestBody Account account) throws IOException {
		signService.signup(account);
		return new GenericResponse<Object>();
	}
	
	@RequestMapping(value="/isUniqueNewUsername", method=RequestMethod.POST)
	public HashMap<String, Boolean> isUniqueUsername(@RequestBody HashMap<String, Object> jsonMap) {
		boolean isUniqueAndNew = accountSerivce.isUniqueNewUsername((String)jsonMap.get("username"));
		HashMap<String, Boolean> ret = new HashMap<>();
		ret.put("isUniqueNewUserName", isUniqueAndNew);
		return ret;
	}
	
	@RequestMapping(value="/sign-in", method=RequestMethod.POST)
	public GenericResponse<?> signin(@RequestBody Account trying, HttpServletRequest req) {
		Account account = signService.signin(trying, req.getSession());
		if(account == null) {
			return GenericResponse.getFail("일치하는 사용자 정보가 없습니다.");
		}else {
			GenericResponse<Account> gr = new GenericResponse<Account>();
			gr.setData(account);
			return gr;
		}
	}

	@RequestMapping(value="/myAccount", method=RequestMethod.GET)
	public Account getMyAccount(HttpServletRequest req) throws NotSignedInException {
		Sign sign = signService.getSign(req.getSession());
		Account mine = sign.getAccount();
		mine.setPassword("");
		return mine;
	}
	
	@RequestMapping(value="/sign-out", method=RequestMethod.POST)
	public GenericResponse<?> signout(HttpServletRequest req) {
		req.getSession().invalidate();
		GenericResponse<Object> res = new GenericResponse<Object>();
		return res;
	}

}
