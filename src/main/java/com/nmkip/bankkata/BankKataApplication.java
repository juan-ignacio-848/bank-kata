package com.nmkip.bankkata;

public class BankKataApplication {

    public static void main(String[] args) {
        Account account = new Account(new Clock(), new TransactionRepository(), new StatementPrinter(new Console()));
        account.deposit(1000);
        account.deposit(1500);
        account.withdraw(700);
        account.deposit(2000);
        account.withdraw(2000);

        account.printStatement();
    }

}
