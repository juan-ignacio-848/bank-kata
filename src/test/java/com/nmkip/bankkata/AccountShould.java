package com.nmkip.bankkata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {

    private static final String NOW = "20/05/2019";

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private Clock clock;

    @Mock
    private StatementPrinter statementPrinter;

    private Account account;

    @Before
    public void setUp() {
        account = new Account(clock, transactionRepository, statementPrinter);
    }

    @Test
    public void deposit_a_transaction() {
        given(clock.todayAsString()).willReturn(NOW);
        Transaction deposit = new Deposit(NOW, 100);

        account.deposit(100);

        verify(transactionRepository).add(deposit);
    }

    @Test
    public void withdraw_a_transaction() {
        given(clock.todayAsString()).willReturn(NOW);
        Transaction withdrawal = new Withdrawal(NOW, 140);

        account.withdraw(140);

        verify(transactionRepository).add(withdrawal);
    }

    @Test
    public void print_transactions_statement() {
        List<Transaction> transactions = singletonList(new Deposit(NOW, 1000));
        given(transactionRepository.allTransactions()).willReturn(transactions);


        account.printStatement();

        verify(statementPrinter).print(transactions);
    }

}