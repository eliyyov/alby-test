package com.alby.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alby.entity.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
	
	@Query(value = "SELECT * FROM transaction WHERE account_id = :accountIdParam", nativeQuery = true)
	List<Transaction> findAllTransactionsByAccountId(@Param("accountIdParam")int accountId);
	
	List<Transaction> findAllByTransactionDateBetween(Date startDate, Date endDate);
}
