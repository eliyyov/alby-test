package com.alby.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.alby.common.GenericResponseDataNotFound;
import com.alby.common.GenericResponseInvalidRequest;
import com.alby.common.GenericResponseOK;
import com.alby.common.TransactionType;
import com.alby.dto.TransactionDto;
import com.alby.entity.Account;
import com.alby.entity.Transaction;
import com.alby.mapper.TransactionMapper;
import com.alby.repository.AccountRepository;
import com.alby.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionMapper transactionMapper;
	
	@Autowired
	KafkaTemplate<String, Transaction> kafkaTemplate;
	
	private static final String TOPIC = "Topic_Alby";
	
	public Object getAllTransaction() {
		List<TransactionDto> result =  transactionMapper.entityToDto((List<Transaction>)transactionRepository.findAll()); ;
		
		if (result == null || result.size() == 0) {
			return new GenericResponseDataNotFound();
		} else {
			return result;
		}		
	}
	
	public Object doTransaction(String input) {
		
		JSONObject jsonObject = new JSONObject(input);
		Transaction transaction = new Transaction();
		int accountId;
		int transactionType;
		TransactionType transactionTypeEnum;
		double transactionAmount;
		String transactionDateString = null;
		Date transactionDate;
		
		try {
			accountId = jsonObject.getInt("accountId");
			transactionType = jsonObject.getInt("transactionType");
			transactionTypeEnum = TransactionType.valueOf(transactionType);
			
			transactionDateString = jsonObject.getString("transactionDate");
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			transactionDate = format.parse(transactionDateString);
			
			transactionAmount = jsonObject.getDouble("transactionAmount");
			
		} catch (ParseException e) {
			return new GenericResponseInvalidRequest(e.toString());
		}
		
		if (transactionDate == null || transactionAmount == 0) {
			return new GenericResponseInvalidRequest();
		} else if (transactionAmount < 0) {
			return new GenericResponseInvalidRequest("Transaction amount is negative");
		} else if (transactionTypeEnum == null) {
			return new GenericResponseInvalidRequest("Transaction type unknown");
		}
		
		Account account = new Account();
		Optional<Account> accountFromDB = accountRepository.findById(accountId);
		
		if(accountFromDB.isPresent()) {
			account = accountFromDB.get();
		} else {
			return new GenericResponseDataNotFound();
		}
		
		transaction.setAccount(account);
		transaction.setTransactionType(transactionTypeEnum);
		transaction.setTransactionAmount(transactionAmount);
		transaction.setTransactionDate(transactionDate);
		
		amountCalculation(account, transaction);
		
		kafkaTemplate.send(TOPIC, transaction);
		
		return new GenericResponseOK();
	}
	
	private void amountCalculation(Account account, Transaction transaction) {
		
		Double accountAmount = account.getAccountAmount();
		
		switch (transaction.getTransactionType().getId()) {
		case 0:
			accountAmount = accountAmount + transaction.getTransactionAmount();
			break;
		case 1:
			accountAmount = accountAmount - transaction.getTransactionAmount();
			break;
		case 2:
			accountAmount = accountAmount - transaction.getTransactionAmount();
			break;
		case 3:
			accountAmount = accountAmount + transaction.getTransactionAmount();
			break;
		}
		
		account.setAccountAmount(accountAmount);
		
		accountRepository.save(account);
		transactionRepository.save(transaction);
		
	}
	
	public Object checkTransactionByDate(String input) {
		
		JSONObject jsonObject = new JSONObject(input);
		String startDateInput;
		String endDateInput;
		Date startDate;
		Date endDate;
		
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			startDateInput = jsonObject.getString("startDate");
			startDate = format.parse(startDateInput);
			
			endDateInput = jsonObject.getString("endDate");
			endDate = format.parse(endDateInput);
		} catch (Exception e) {
			return new GenericResponseInvalidRequest(e.toString());
		}
		
		if (startDate.after(endDate)) {
			return new GenericResponseInvalidRequest("End Date is older then Start Date");
		} else if(startDate.after(new Date(System.currentTimeMillis()))) {
			return new GenericResponseInvalidRequest("Start Date is in the future");
		}
		
		List<TransactionDto> result = transactionMapper.entityToDto(transactionRepository.findAllByTransactionDateBetween(startDate, endDate));
		
		return result;
	}
	
	public Object checkTransactionById(String input) {
		
		JSONObject jsonObject = new JSONObject(input);
		int accountId;
		
		try {
			accountId = jsonObject.getInt("accountId");
		} catch (Exception e) {
			return new GenericResponseInvalidRequest(e.toString());
		}
		
		List<TransactionDto> result =  transactionMapper.entityToDto(transactionRepository.findAllTransactionsByAccountId(accountId));
		
		if(!result.isEmpty()) {
			return result;
		} else {
			return new GenericResponseDataNotFound();
		}
	}
}
