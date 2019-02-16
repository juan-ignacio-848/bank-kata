package com.nmkip.bankkata;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {

    private static final String HEADER = "DATE | AMOUNT | BALANCE";

    private final NumberFormat decimalFormat = new DecimalFormat("#.00");
    private final Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    void print(List<Transaction> transactions) {
        console.printLine(HEADER);
        printStatementBody(transactions);
    }

    private void printStatementBody(List<Transaction> transactions) {
        AtomicInteger runningBalance = new AtomicInteger(0);
        transactions.stream()
                .map(transaction -> statementLineFor(transaction, runningBalance))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(console::printLine);
    }

    private String statementLineFor(Transaction transaction, AtomicInteger runningBalance) {
        return transaction.date()
                + " | "
                + decimalFormat.format(transaction.amount())
                + " | "
                + decimalFormat.format(runningBalance.addAndGet(transaction.amount()));
    }
}
