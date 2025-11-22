package main.com.ApexLending.bank.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import main.com.ApexLending.bank.model.Transaction;
import main.com.ApexLending.bank.model.TransactionType;

public class TransactionService {
	private final List<Transaction> allTransactions = new ArrayList<>();
	
	public void recordTransaction(String accountId, TransactionType type, double amount, String note) {
        Transaction t = new Transaction(accountId, type, amount, note);
        allTransactions.add(t);
    }
	
	 public List<Transaction> getAllTransactions() {
	        return Collections.unmodifiableList(allTransactions);
	 }
	 
	 public List<Transaction> getTransactionsForAccount(String accountId) {
	        return allTransactions.stream()
	                .filter(t -> t.getAccountId().equals(accountId))
	                .collect(Collectors.toList());
	 }
	 
	 public List<Transaction> getTransactionsBetween(String accountId, LocalDateTime start, LocalDateTime end) {
	        return allTransactions.stream()
	                .filter(t -> t.getAccountId().equals(accountId))
	                .filter(t -> t.getTimestamp().isAfter(start) && t.getTimestamp().isBefore(end))
	                .collect(Collectors.toList());
	 }
}
