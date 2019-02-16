package com.nmkip.bankkata;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionRepositoryShould {

    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        transactionRepository = new TransactionRepository();
    }

    @Test
    public void store_a_deposit_transaction() {
        Transaction deposit = new Deposit("25/02/2019", 450);

        transactionRepository.add(deposit);
        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactions.get(0)).isEqualTo(deposit);
    }

    @Test
    public void store_a_withdrawal_transaction() {
        Transaction withdrawal = new Withdrawal("25/02/2019", 450);

        transactionRepository.add(withdrawal);
        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactions.get(0)).isEqualTo(withdrawal);
    }
}