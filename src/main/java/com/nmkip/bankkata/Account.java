package com.nmkip.bankkata;

public class Account {

    private final TransactionRepository transactionRepository;
    private final Clock clock;
    private final StatementPrinter statementPrinter;

    public Account(Clock clock, TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        Transaction deposit = new Deposit(clock.todayAsString(), amount);
        transactionRepository.add(deposit);
    }

    public void withdraw(int amount) {
        Transaction withdrawal = new Withdrawal(clock.todayAsString(), amount);
        transactionRepository.add(withdrawal);
    }

    public void printStatement() {
        statementPrinter.print(transactionRepository.allTransactions());
    }
}
