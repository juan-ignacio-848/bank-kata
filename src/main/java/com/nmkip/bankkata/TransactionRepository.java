package com.nmkip.bankkata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {

    private List<Transaction> transactions;

    public TransactionRepository() {
        this.transactions = new ArrayList<>();
    }

    void add(Transaction transaction) {
        transactions.add(transaction);
    }

    List<Transaction> allTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
