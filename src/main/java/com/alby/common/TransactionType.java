package com.alby.common;

public enum TransactionType {

    DEPOSIT(0), WITHDRAWAL(1), LOAN(2), PAY(3);
    int id;

    TransactionType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
	public static TransactionType valueOf(int intValue) {
		switch (intValue) {
		case 0:
			return DEPOSIT;
		case 1:
			return WITHDRAWAL;
		case 2:
			return LOAN;
		case 3:
			return PAY;
		default:
			return null;
		}
	}
}
