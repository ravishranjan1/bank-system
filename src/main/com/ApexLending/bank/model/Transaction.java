package main.com.ApexLending.bank.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
	private final String id;
	private final String accountId;
	private final TransactionType type;
	private final double amount;
	private final LocalDateTime timestamp;
	private final String note;

	public Transaction(String accountId, TransactionType type, double amount, String note) {
		this.id = UUID.randomUUID().toString();
		this.accountId = accountId;
		this.type = type;
		this.amount = amount;
		this.timestamp = LocalDateTime.now();
		this.note = note;
	}

	public String getId() {
		return id;
	}

	public String getAccountId() {
		return accountId;
	}

	public TransactionType getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getNote() {
		return note;
	}

	@Override
	public String toString() {
		return String.format("%s | %s | %.2f | %s | %s", id, type, amount, timestamp, note == null ? "" : note);
	}
	

}
