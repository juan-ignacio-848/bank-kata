package com.nmkip.bankkata;

import java.util.Objects;

abstract class Transaction {
    private final String date;
    private final int amount;

    Transaction(String date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return amount == that.amount &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                '}';
    }

    String date() {
        return date;
    }

    int amount() {
        return amount;
    }
}
