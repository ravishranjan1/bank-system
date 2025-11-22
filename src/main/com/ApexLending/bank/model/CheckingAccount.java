package main.com.ApexLending.bank.model;

import java.time.LocalDateTime;

import main.com.ApexLending.bank.exceptions.InsufficientFundsException;

public class CheckingAccount extends Account {
	private double overdraftLimit;

	public CheckingAccount(String accountId, String customerId, double initial, double overdraftLimit) {
		super(accountId, customerId, initial);
		this.overdraftLimit = overdraftLimit;
	}

	@Override
	protected boolean canWithdraw(double amount) {
		if (balance + overdraftLimit >= amount) {
			return true;
		} else {
			return false;
		}
	}

	public double getOverdraftLimit() {
		return overdraftLimit;
	}
}
