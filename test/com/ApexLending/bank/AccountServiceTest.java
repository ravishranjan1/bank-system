package com.ApexLending.bank;

import main.com.ApexLending.bank.exceptions.*;
import main.com.ApexLending.bank.model.*;
import main.com.ApexLending.bank.service.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

	@Test
	void testDeposit() throws BankException {
		AccountService service = new AccountService();
		Account acc = service.createChecking("C101", 1000, 200);
		service.deposit(acc.getAccountId(), 500);
		assertEquals(1500, acc.getBalance());
	}

	@Test
	void testWithdraw() throws BankException {
		AccountService service = new AccountService();
		Account acc = service.createChecking("C101", 1000, 200);
		service.withdraw(acc.getAccountId(), 200);
		assertEquals(800, acc.getBalance());
	}

	@Test
	void testTransfer() throws BankException {
		AccountService service = new AccountService();
		Account a1 = service.createChecking("C1", 1000, 100);
		Account a2 = service.createChecking("C2", 500, 100);

		service.transfer(a1.getAccountId(), a2.getAccountId(), 300);

		assertEquals(700, a1.getBalance());
		assertEquals(800, a2.getBalance());
	}
}
