package main.java.com.ApexLending.bank;

import java.util.Scanner;

import main.java.com.ApexLending.bank.exceptions.*;
import main.java.com.ApexLending.bank.service.*;
import main.java.com.ApexLending.bank.model.*;

public class App {
	private static final CustomerService customerService = new CustomerService();
	private static final AccountService accountService = new AccountService();

	public static void main(String[] args) {
		seedData();
		System.out.println("Welcome to Simple Bank (console)");
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("\ncommand (help for list) > ");
			String cmd = sc.next();
			try {
				switch (cmd) {
				case "help":
					printHelp();
					break;
				case "add-customer":
					addCustomer(sc);
					break;
				case "list-customers":
					listCustomers();
					break;
				case "create-account":
					createAccount(sc);
					break;
				case "list-accounts":
					listAccounts();
					break;
				case "deposit":
					deposit(sc);
					break;
				case "withdraw":
					withdraw(sc);
					break;
				case "transfer":
					transfer(sc);
					break;
				case "statement":
					statement(sc);
					break;
				case "apply-interest":
					applyInterest();
					break;
				case "exit":
					System.out.println("Goodbye");
					return;
				default:
					System.out.println("Unknown command");
				}
			} catch (BankException e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		}
	}


	private static void printHelp() {
		System.out.println(
				"help, add-customer, list-customers, create-account, list-accounts, deposit, withdraw, transfer, statement, apply-interest, exit");
	}

	private static void addCustomer(Scanner sc) {
		System.out.print("name: ");
		String name = sc.next();
		System.out.print("phone: ");
		String phone = sc.next();
		System.out.print("email: ");
		String email = sc.next();
		var c = customerService.addCustomer(name, phone, email);
		System.out.println("Added: " + c);
	}

	private static void listCustomers() {
		customerService.all().forEach(System.out::println);
	}

	private static void createAccount(Scanner sc) {
		System.out.print("customerId: ");
		String cid = sc.next();
		System.out.print("type (checking/savings): ");
		String type = sc.next();
		System.out.print("initial deposit: ");
		double init = sc.nextDouble();
		if ("checking".equalsIgnoreCase(type)) {
			System.out.print("overdraft: ");
			double od = sc.nextDouble();
			var a = accountService.createChecking(cid, init, od);
			System.out.println("Created: " + a);
		} else {
			System.out.print("annualRate (e.g. 0.03): ");
			double rate = sc.nextDouble();
			var a = accountService.createSavings(cid, init, rate);
			System.out.println("Created: " + a);
		}
	}

	private static void listAccounts() {
		accountService.all().values().forEach(System.out::println);
	}

	private static void deposit(Scanner sc) throws BankException {
		System.out.print("accountId: ");
		String aid = sc.next();
		System.out.print("amount: ");
		double amt = sc.nextDouble();
		accountService.deposit(aid, amt);
		System.out.println("Deposited");
	}

	private static void withdraw(Scanner sc) throws BankException {
		System.out.print("accountId: ");
		String aid = sc.next();
		System.out.print("amount: ");
		double amt = sc.nextDouble();
		accountService.withdraw(aid, amt);
		System.out.println("Withdrawn");
	}

	private static void transfer(Scanner sc) throws BankException {
		System.out.print("fromAccountId: ");
		String from = sc.next();
		System.out.print("toAccountId: ");
		String to = sc.next();
		System.out.print("amount: ");
		double amt = sc.nextDouble();
		accountService.transfer(from, to, amt);
		System.out.println("Transferred");
	}

	private static void statement(Scanner sc) throws AccountNotFoundException {
		System.out.print("accountId: ");
		String aid = sc.next();
		var tx = accountService.getTransactionService().getTransactionsForAccount(aid);
		tx.forEach(System.out::println);
	}

	private static void applyInterest() {
		accountService.all().values().forEach(a -> {
			if (a instanceof main.java.com.ApexLending.bank.model.SavingsAccount) {
				double added = ((main.java.com.ApexLending.bank.model.SavingsAccount) a).applyMonthlyInterest();
				if (added > 0)
					System.out.println("Interest added to " + a.getAccountId() + " : " + added);
			}
		});
	}

	private static void seedData() {
		var c1 = customerService.addCustomer("Ravish", "9135275832", "ravishranjan@gmail.com");
		var c2 = customerService.addCustomer("Ashutosh", "7601436581", "ashu.aa@gmail.com");
		accountService.createChecking(c1.getId(), 1000.0, 500.0);
		accountService.createSavings(c2.getId(), 2000.0, 0.03);
	}
}
