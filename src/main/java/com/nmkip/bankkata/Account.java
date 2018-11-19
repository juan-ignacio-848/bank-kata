package com.nmkip.bankkata;

public class Account {

    private TransactionRepository transactionRepository;

    public Account(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void deposit(int amount) {
        transactionRepository.addDeposit(100);
    }

    public void withdraw(int amount) {

    }

    public void printStatement() {

    }
}
