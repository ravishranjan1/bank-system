package main.java.com.ApexLending.bank.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Account {
	protected String accountId;
	protected String customerId;
	protected double balance;
	protected boolean active;
	protected LocalDate openedDate;
	protected List<Transaction> transactions;

	
	
	public Account(String accountId, String customerId, double balance, boolean active, LocalDate openedDate) {
		this.accountId = accountId;
		this.customerId = customerId;
		this.balance = balance;
		this.active = active;
		this.openedDate = openedDate;
	}

	public void deposit(double amount,LocalDateTime timeStamp) {
		balance = balance + amount;
		transactions.add(new Transaction("TXN-DP", accountId, TransactionType.DEPOSIT, amount, timeStamp, "Deposit"));
	}

	public abstract void withdraw(double amount,LocalDateTime timeStamp);
	
	public String getAccountId() {
		return accountId;
	}
	public double getBalance() {
		return balance;
	}
	public List<Transaction> getTransactions(){
		return transactions;
	}
}
