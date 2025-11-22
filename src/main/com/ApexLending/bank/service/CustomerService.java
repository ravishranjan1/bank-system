package main.com.ApexLending.bank.service;

import main.com.ApexLending.bank.model.Customer;
import main.com.ApexLending.bank.util.IdGenerator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
	private Map<String, Customer> customers = new HashMap<>();

	public Customer addCustomer(String name, String phone, String email) {
		String id = IdGenerator.nextCustomerId();
		Customer c = new Customer(id, name, phone, email);
		customers.put(id, c);
		return c;
	}

	public Customer findById(String id) {
		return customers.get(id);
	}

	public Collection<Customer> all() {
		return customers.values();
	}
}
