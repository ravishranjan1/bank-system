package main.com.ApexLending.bank.exceptions;

public class AccountNotFoundException extends BankException{

	public AccountNotFoundException(String id) {
		super("Account not Found : "+id);
	}
	
}
