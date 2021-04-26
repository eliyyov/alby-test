package com.alby.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alby.dto.TransactionDto;
import com.alby.entity.Account;
import com.alby.entity.Transaction;
import com.alby.repository.AccountRepository;

@Component
public class TransactionMapper {
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private AccountRepository accountRepository;
	
	private Account account;
	
	private void initRelation(int accountId) {
		account = accountRepository.findById(accountId).orElse(null);
		
		if (account == null) {
			//error message or any
		}
	}
	
	public Transaction dtoToEntity(TransactionDto dto) {
		initRelation(dto.getAccount().getAccountId());
		
		
		Transaction entity = new Transaction();
		entity.setTransactionId(dto.getTransactionId());
		entity.setAccount(account);
		entity.setTransactionType(dto.getTransactionType());
		entity.setTransactionAmount(dto.getTransactionAmount());
		entity.setTransactionDate(dto.getTransactionDate());
		
		return entity;
	}
	
	public TransactionDto entityToDto(Transaction entity) {
		TransactionDto dto = new TransactionDto();
		dto.setTransactionId(entity.getTransactionId());
		dto.setAccount(accountMapper.entityToDtoSimple(entity.getAccount()));
		dto.setTransactionType(entity.getTransactionType());
		dto.setTransactionAmount(entity.getTransactionAmount());
		dto.setTransactionDate(entity.getTransactionDate());
		
		return dto;
	}
	
	public TransactionDto entityToDtoSimple(Transaction entity) {
		TransactionDto dto = new TransactionDto();
		dto.setTransactionId(entity.getTransactionId());
		
		return dto;
	}
	
	
	public List<Transaction> dtoToEntity(List<TransactionDto> dto) {
		return dto.stream().map(this::dtoToEntity).collect(Collectors.toList()); 
	}
	
	public List<TransactionDto> entityToDto(List<Transaction> entities) {
		return entities.stream().map(this::entityToDto).collect(Collectors.toList()); 
	}
	
	public List<TransactionDto> entityToDtoSimple(List<Transaction> entities) {
		return entities.stream().map(this::entityToDtoSimple).collect(Collectors.toList()); 
	}

}
