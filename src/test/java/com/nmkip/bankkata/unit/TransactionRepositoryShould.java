package com.nmkip.bankkata.unit;

import com.nmkip.bankkata.Clock;
import com.nmkip.bankkata.Transaction;
import com.nmkip.bankkata.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {

    private static final String TODAY = "12/05/2015";

    private TransactionRepository transactionRepository;
    @Mock Clock clock;

    @Before
    public void setUp() {
        transactionRepository = new TransactionRepository(clock);
        given(clock.todayAsString()).willReturn(TODAY);
    }

    @Test
    public void create_and_store_a_deposit_transaction() {
        transactionRepository.addDeposit(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactions.get(0)).isEqualTo(transaction(TODAY, 100));
    }

    @Test
    public void create_and_store_a_withdrawal_transaction() {
        transactionRepository.addWithdrawal(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size()).isEqualTo(1);
        assertThat(transactions.get(0)).isEqualTo(transaction(TODAY, -100));
    }

    private Transaction transaction(String date, int amount) {
        return new Transaction(date, amount);
    }
}