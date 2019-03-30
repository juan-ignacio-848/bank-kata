package com.nmkip.bankkata;

class Withdrawal extends Transaction {

    Withdrawal(String date, int amount) {
        super(date, -amount);
    }

}
