package main.java.com.ApexLending.bank.service;

import java.util.Map;
import java.util.HashMap;

import main.java.com.ApexLending.bank.exceptions.AccountNotFoundException;
import main.java.com.ApexLending.bank.model.*;
import main.java.com.ApexLending.bank.util.IdGenerator;

public class AccountService {
	private final Map<String, Account> accounts = new HashMap<>();
	private final TransactionService transactionService = new TransactionService();
	
	public Account createChecking(String customerId,double initial,double overdraft) {
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
	        if (a == null) throw new AccountNotFoundException(id);
	        return a;
	 }
	 
	 public void closeAccount(String id) throws AccountNotFoundException {
	        Account a = findById(id);
	        a.close();
	 }
}
