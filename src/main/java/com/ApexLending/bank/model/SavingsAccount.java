package main.java.com.ApexLending.bank.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import main.java.com.ApexLending.bank.exceptions.InsufficientFundsException;

public class SavingsAccount extends Account{
	private double annualInterestRate;
	public SavingsAccount(String accountId, String customerId, double balance, boolean active, LocalDate openedDate, double annualInterestRate) {
		super(accountId, customerId, balance, active, openedDate);
		this.annualInterestRate = annualInterestRate;
				
	}

	public void applyMonthlyInterest() {
		double monthlyInterest = balance * (annualInterestRate/12);
		System.out.println("Monthly Interest : "+monthlyInterest);
	}

	@Override
	public void withdraw(double amount, LocalDateTime timeStamp) {
		if(balance<amount) {
			throw new InsufficientFundsException(amount);
		}
		balance = balance - amount;
		transactions.add(new Transaction("TXN-WD", accountId, TransactionType.WITHDRAWAL, amount, timeStamp,"Withdrawal"));
	}

}
