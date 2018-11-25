package com.nmkip.bankkata;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {

    private Console console;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void printStatement(List<Transaction> transactions) {
        console.printLine("DATE | AMOUNT | BALANCE");

        AtomicInteger runningBalance = new AtomicInteger(0);
        transactions.stream()
                .map(transaction -> printTransaction(transaction, runningBalance))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(console::printLine);
    }

    private String printTransaction(Transaction transaction, AtomicInteger runningBalance) {
        return transaction.date() +
                " | " +
                decimalFormat.format(transaction.amount()) +
                " | " +
                decimalFormat.format(runningBalance.addAndGet(transaction.amount()));
    }
}
