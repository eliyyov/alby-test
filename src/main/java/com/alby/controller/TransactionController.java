package com.alby.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alby.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping(path = "/getAllTransaction", method = RequestMethod.GET, produces = "application/json")
	public Object getAccountsgetTransactions() throws Exception, ParseException {
		return transactionService.getAllTransaction();
	}
	
	@RequestMapping(path = "/doTransaction", method = RequestMethod.POST, produces = "application/json")
	public Object doTransaction(@RequestBody String input) throws Exception, ParseException {
		return transactionService.doTransaction(input);
	}
	
	@RequestMapping(path = "/checkTransactionByDate", method = RequestMethod.POST, produces = "application/json")
	public Object checkTransaction(@RequestBody String input) throws Exception, ParseException {
		return transactionService.checkTransactionByDate(input);
	}
	
	@RequestMapping(path = "/checkTransactionById", method = RequestMethod.POST, produces = "application/json")
	public Object checkTransactionById(@RequestBody String input) throws Exception, ParseException {
		return transactionService.checkTransactionById(input);
	}
}
