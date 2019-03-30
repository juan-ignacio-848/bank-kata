package com.nmkip.bankkata;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class TransactionRepositoryShould {

    @Test
    public void store_a_transaction() {
        TransactionRepository transactionRepository = new TransactionRepository();
        Transaction withdrawal = new Withdrawal("27/02/2019", 300);

        transactionRepository.add(withdrawal);

        assertThat(transactionRepository.allTransactions(), hasItem(withdrawal));
    }
}