package com.alby.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alby.service.AccountService;

@RestController
public class AccountController{
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(path = "/getAllAccount", method = RequestMethod.GET, produces = "application/json")
	public Object getAccounts() throws Exception, ParseException {
		return accountService.getAllAccount();
	}
	
	@RequestMapping(path = "/createAccount", method = RequestMethod.POST, produces = "application/json")
	public Object createAccount(@RequestBody String input) throws Exception, ParseException {
		return accountService.createAccount(input);
	}
}
