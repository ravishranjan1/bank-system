package main.java.com.ApexLending.bank.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import main.java.com.ApexLending.bank.exceptions.InsufficientFundsException;

public class CheckingAccount extends Account {
	private double overdraftLimit;
	public CheckingAccount(String accountId, String customerId, double balance, boolean active, LocalDate openedDate, double overdraftLimit) {
		super(accountId, customerId, balance, active, openedDate);
		this.overdraftLimit = overdraftLimit;
	}

	

	@Override
	public void withdraw(double amount, LocalDateTime timeStamp) {
		if (balance + overdraftLimit < amount) {
			throw new InsufficientFundsException(amount);
		}
		balance = balance - amount;
		transactions.add(new Transaction("TXN-WD", accountId, TransactionType.WITHDRAWAL, amount, timeStamp, "Withdrawal"));
	}

}
