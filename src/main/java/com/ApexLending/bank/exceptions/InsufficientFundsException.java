package main.java.com.ApexLending.bank.exceptions;

public class InsufficientFundsException extends BankException{

	public InsufficientFundsException(double amount) {
		super("Insufficient Funds for amount"+amount);
	}
	
}
