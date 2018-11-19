package com.nmkip.bankkata;

public class BankKataApplication {

    public static void main(String[] args) {
        StatementPrinter statementPrinter = new StatementPrinter(new Console());
        TransactionRepository transactionRepository = new TransactionRepository(new Clock());
        Account account = new Account(transactionRepository, statementPrinter);
        account.deposit(100);
        account.deposit(100);
        account.deposit(1000);
        account.deposit(1000);
        account.withdraw(400);
        account.withdraw(400);
        account.withdraw(400);
        account.deposit(345);
        account.printStatement();
    }

}
