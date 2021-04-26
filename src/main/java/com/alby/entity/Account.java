package com.alby.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	
	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "account_dob")
	private Date accountDob;
	
	@Column(name = "account_address")
	private String accountAddress;
	
	@Column(name = "account_amount")
	private Double accountAmount;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getAccountDob() {
		return accountDob;
	}

	public void setAccountDob(Date accountDob) {
		this.accountDob = accountDob;
	}

	public String getAccountAddress() {
		return accountAddress;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public Double getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(Double accountAmount) {
		this.accountAmount = accountAmount;
	}
}
