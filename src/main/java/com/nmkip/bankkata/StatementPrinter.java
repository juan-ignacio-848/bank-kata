package com.nmkip.bankkata;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {

    private static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";

    private final NumberFormat formatter = new DecimalFormat("#.00");
    private final Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    void print(List<Transaction> transactions) {
        console.printLine(STATEMENT_HEADER);
        printStatementLines(transactions);
    }

    private void printStatementLines(List<Transaction> transactions) {
        AtomicInteger runningBalance = new AtomicInteger(0);
        transactions.stream()
                    .map(transaction -> asStatementLine(transaction, runningBalance))
                    .collect(Collectors.toCollection(LinkedList::new))
                    .descendingIterator()
                    .forEachRemaining(console::printLine);
    }


    private String asStatementLine(Transaction transaction, AtomicInteger runningBalance) {
        return transaction.date() +
                " | " +
                formatter.format(transaction.amount()) +
                " | " +
                formatter.format(runningBalance.addAndGet(transaction.amount()));
    }


}
