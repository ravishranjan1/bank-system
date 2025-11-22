package main.com.ApexLending.bank.service;

import java.util.Map;
import java.util.HashMap;

import main.com.ApexLending.bank.exceptions.*;
import main.com.ApexLending.bank.model.*;
import main.com.ApexLending.bank.util.IdGenerator;

public class AccountService {
	private final Map<String, Account> accounts = new HashMap<>();
	private final TransactionService transactionService = new TransactionService();

	public Account createChecking(String customerId, double initial, double overdraft) {
		String id = IdGenerator.nextAccountId();
		Account a = new CheckingAccount(id, customerId, initial, overdraft);
		accounts.put(id, a);
		return a;

	}

	public Account createSavings(String customerId, double initial, double rate) {
		String id = IdGenerator.nextAccountId();
		Account a = new SavingsAccount(id, customerId, initial, rate);
		accounts.put(id, a);
		return a;
	}

	public Account findById(String id) throws AccountNotFoundException {
		Account a = accounts.get(id);
		if (a == null)
			throw new AccountNotFoundException(id);
		return a;
	}

	public void closeAccount(String id) throws AccountNotFoundException {
		Account a = findById(id);
		a.close();
	}

	public void deposit(String id, double amount) throws BankException {
		Account a = findById(id);
		a.deposit(amount);
		transactionService.recordTransaction(id, TransactionType.DEPOSIT, amount, null);
	}

	public void withdraw(String id, double amount) throws BankException {
		Account a = findById(id);
		a.withdraw(amount);
		transactionService.recordTransaction(id, TransactionType.WITHDRAWAL, amount, null);
	}

	public void transfer(String fromId, String toId, double amount) throws BankException {
		if (amount <= 0)
			throw new BankException("Amount must be positive");
		Account from = findById(fromId);
		Account to = findById(toId);
		synchronized (this) {
			from.withdraw(amount);
			to.deposit(amount);
		}
		transactionService.recordTransaction(fromId, TransactionType.TRANSFER, amount, "Transferred to " + toId);
		transactionService.recordTransaction(toId, TransactionType.TRANSFER, amount, "Received from " + fromId);
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}
	
	public Map<String, Account> all() { 
		return accounts; 
	}
}
