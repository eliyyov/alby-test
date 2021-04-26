package com.alby.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.alby.dto.AccountDto;
import com.alby.entity.Account;

@Component
public class AccountMapper {

	public Account dtoToEntity(AccountDto dto) {
		Account entity = new Account();
		entity.setAccountId(dto.getAccountId());
		entity.setAccountName(dto.getAccountName());
		
		return entity;
	}
	
	public AccountDto entityToDto(Account entity) {
		AccountDto dto = new AccountDto();
		dto.setAccountId(entity.getAccountId());
		dto.setAccountName(entity.getAccountName());
		
		return dto;
	}
	
	public AccountDto entityToDtoSimple(Account entity) {
		AccountDto dto = new AccountDto();
		dto.setAccountId(entity.getAccountId());
		dto.setAccountName(entity.getAccountName());
		
		return dto;
	}
	
	public List<Account> dtoToEntity(List<AccountDto> dto) {
		return dto.stream().map(this::dtoToEntity).collect(Collectors.toList()); 
	}
	
	public List<AccountDto> entityToDto(List<Account> entities) {
		return entities.stream().map(this::entityToDto).collect(Collectors.toList()); 
	}

	
	public List<AccountDto> entityToDtoSimple(List<Account> entities) {
		return entities.stream().map(this::entityToDtoSimple).collect(Collectors.toList()); 
	}
	
}
