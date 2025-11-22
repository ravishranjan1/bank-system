package main.com.ApexLending.bank.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
	private static final AtomicInteger acctSeq = new AtomicInteger(100);
	private static final AtomicInteger custSeq = new AtomicInteger(100);

	public static String nextAccountId() {
		return "A" + acctSeq.getAndIncrement();
	}

	public static String nextCustomerId() {
		return "C" + custSeq.getAndIncrement();
	}
}
