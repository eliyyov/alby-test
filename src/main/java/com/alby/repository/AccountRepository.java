package com.alby.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alby.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{
	
}
