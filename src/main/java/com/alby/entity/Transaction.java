package com.alby.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alby.common.TransactionType;

@Entity
@Table(name="transaction")
public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "transaction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
//	@ManyToOne(targetEntity = Account.class, fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@ManyToOne(targetEntity = Account.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;
	
	@Column(name = "transaction_type")
	private TransactionType transactionType;
	
	@Column(name = "transaction_amount")
	private Double transactionAmount;
	
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}
