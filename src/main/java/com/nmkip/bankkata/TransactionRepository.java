package com.nmkip.bankkata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {

    private List<Transaction> transactions = new ArrayList<>();

    void addDeposit(Transaction transaction) {
        transactions.add(transaction);
    }

    void addWithdrawal(Transaction transaction) {
        transactions.add(transaction);
    }

    List<Transaction> allTransactions() {
        return Collections.unmodifiableList(transactions);
    }
}
