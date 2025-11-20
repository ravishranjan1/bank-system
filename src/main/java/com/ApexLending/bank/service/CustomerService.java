package main.java.com.ApexLending.bank.service;

import java.util.HashMap;
import java.util.Map;

import main.java.com.ApexLending.bank.model.Customer;

public class CustomerService {
	private Map<String, Customer> customers = new HashMap<>();
	private void addCustomer(Customer c) {
		customers.put(c.getId(), c);
	}
	private Customer findById(String id) {
		return customers.get(id);
	}
}
