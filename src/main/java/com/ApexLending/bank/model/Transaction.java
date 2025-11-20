package main.java.com.ApexLending.bank.model;

import java.time.LocalDateTime;

public class Transaction {
	private String id;
	private String accountId;
	private TransactionType type;
	private double amount;
	private LocalDateTime timeStamp;
	private String note;
	public Transaction(String id, String accountId, TransactionType type, double amount, LocalDateTime timeStamp,
			String note) {
		this.id = id;
		this.accountId = accountId;
		this.type = type;
		this.amount = amount;
		this.timeStamp = timeStamp;
		this.note = note;
	}
	
}
