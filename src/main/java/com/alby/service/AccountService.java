package com.alby.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alby.common.GenericResponseDataNotFound;
import com.alby.common.GenericResponseInvalidRequest;
import com.alby.common.GenericResponseOK;
import com.alby.entity.Account;
import com.alby.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	public Object getAllAccount() {
		
		List<Account> result = (List<Account>) accountRepository.findAll();
		
		if (result == null || result.size() == 0) {
			return new GenericResponseDataNotFound();
		} else {
			return result;
		}		
	}
	
	public Object createAccount(String input) {
		
		JSONObject jsonObject = new JSONObject(input);
		Account account = new Account();
		String accountName = null;
		String accountDob;
		Date accountDobDate = null;
		String accountAddress = null;
		
		try {
			accountName = jsonObject.getString("accountName");
		
			accountDob = jsonObject.getString("accountDob");
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			accountDobDate = format.parse(accountDob);
			
			accountAddress = jsonObject.getString("accountAddress");
		} catch (ParseException e) {
			return new GenericResponseInvalidRequest(e.toString());
		}
		
		if (accountName == null || accountDobDate == null || accountAddress == null ||
				accountName.equals("") || accountAddress.equals("")) {
			return new GenericResponseInvalidRequest();
		} else if(accountName.length() < 3) {
			return new GenericResponseInvalidRequest("Name length cannot be less than 3 characters");
		}
		
		account.setAccountName(accountName);
		account.setAccountDob(accountDobDate);
		account.setAccountAddress(accountAddress);
		account.setAccountAmount(0.0);
		
		accountRepository.save(account);
		
		return new GenericResponseOK();
	}
}
