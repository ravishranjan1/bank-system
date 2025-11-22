package main.com.ApexLending.bank.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.com.ApexLending.bank.exceptions.BankException;

public abstract class Account {
	protected final String accountId;
	protected final String customerId;
	protected double balance;
	protected boolean active;
	protected final LocalDate openedDate;
	protected final List<Transaction> transactions = new ArrayList<>();

	public Account(String accountId, String customerId, double initial) {
		this.accountId = accountId;
		this.customerId = customerId;
		this.balance = initial;
		this.active = true;
		this.openedDate = LocalDate.now();
	}

	public String getAccountId() {
		return accountId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public double getBalance() {
		return balance;
	}

	public boolean isActive() {
		return active;
	}

	public void close() {
		this.active = false;
	}

	protected void validateActive() throws BankException {
		if (!active) {
			throw new BankException("Account is closed");
		}
	}

	public synchronized void deposit(double amount) throws BankException {
		validateActive();
		if (amount <= 0) {
			throw new BankException("Amount must be positive");
		}
		balance = balance + amount;
		transactions.add(new Transaction(accountId, TransactionType.DEPOSIT, amount, null));
	}

	public synchronized void withdraw(double amount) throws BankException {
		validateActive();
		if (amount <= 0) {
			throw new BankException("Amount must be positive");
		}
		if (!canWithdraw(amount)) {
			throw new BankException("Insufficient funds / overdraft limit exceeded");
		}
		balance = balance - amount;
		transactions.add(new Transaction(accountId, TransactionType.WITHDRAWAL, amount, null));
	}

	protected abstract boolean canWithdraw(double amount);

	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions);
	}

	@Override
	public String toString() {
		return accountId + " | " + customerId + " | " + this.getClass().getSimpleName() + " | " + balance + " | active="
				+ active;
	}
}
