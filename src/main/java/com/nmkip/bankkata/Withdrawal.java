package com.nmkip.bankkata;

public class Withdrawal extends Transaction {

    Withdrawal(String date, int amount) {
        super(date, -amount);
    }
}
