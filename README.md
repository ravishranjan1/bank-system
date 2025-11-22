ApexLending Banking Console Application

This project is a simple console-based banking management system built in Java. It demonstrates Object-Oriented Programming,
exception handling, and layered architecture using services and models.

Overview
The application allows users to manage:
Customers
Accounts (Checking & Savings)
Deposits, Withdrawals, Transfers
Monthly Interest on Savings Accounts

Project Structure
com.ApexLending.bank
│
├── model
│   ├── Account.java
│   ├── CheckingAccount.java
│   ├── SavingsAccount.java
│   ├── Customer.java
│   ├── AccountType.java        
│   ├── Transaction.java       
│   └── TransactionType.java  
│
├── service
│   ├── CustomerService.java
│   ├── AccountService.java
│   └── TransactionService.java
│
├── exceptions
│   ├── InsufficientFundsException.java
│   ├── AccountNotFoundException.java 
│   └── BankException.java            
│
├── util
│   └── IdGenerator.java 
│
└── App.java


✨ Features

👤 Customer Services
Add and manage customers
Auto-generate unique customer IDs
Store name, phone, and email

🏦 Account Services
Create Checking Account (with overdraft limit)
Create Savings Account (with interest rate)
Auto-generate unique account IDs
AccountType

💰 Transaction Services
Create transaction logs
Transfer between accounts
Deposit & withdraw recorded as transactions
Types: DEPOSIT, WITHDRAW, TRANSFER (via TransactionType enum)

🔁 Monthly Processing
Automatically apply interest to all savings accounts

🧱 Technologies Used
Java OOP 
Enums
Custom Exceptions
Services Layer
Utility Package
CLI Menu using Scanner

🚧 Future Enhancements
Persistent storage (file or DB)
Web or GUI front-end
Authentication
Transaction history UI
