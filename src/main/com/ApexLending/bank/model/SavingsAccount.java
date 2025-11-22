package main.com.ApexLending.bank.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import main.com.ApexLending.bank.exceptions.InsufficientFundsException;

public class SavingsAccount extends Account {

	private double annualInterestRate;

	public SavingsAccount(String accountId, String customerId, double initial, double annualInterestRate) {
		super(accountId, customerId, initial);
		this.annualInterestRate = annualInterestRate;
	}

	@Override
	protected boolean canWithdraw(double amount) {
		return balance - amount >= 0;
	}
	
	public synchronized double applyMonthlyInterest() {
		double monthly = balance * (annualInterestRate/12);
		if(monthly>0) {
			balance = balance + monthly;
			transactions.add(new Transaction(accountId,TransactionType.Interest,monthly,"Monthly Interest"));
		}
		return monthly;
	}

}
