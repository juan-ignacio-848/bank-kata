package com.nmkip.bankkata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {

    @Mock
    private Clock clock;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private StatementPrinter statementPrinter;

    private Account account;

    @Before
    public void setUp() {
        account = new Account(clock, transactionRepository, statementPrinter);
    }

    @Test
    public void store_a_deposit_transaction() {
        given(clock.todayAsString()).willReturn("22/03/2019");

        account.deposit(250);

        verify(transactionRepository).addDeposit(new Deposit("22/03/2019", 250));
    }

    @Test
    public void store_a_withdrawal_transaction() {
        given(clock.todayAsString()).willReturn("25/04/2019");

        account.withdraw(250);

        verify(transactionRepository).addWithdrawal(new Withdrawal("25/04/2019", 250));
    }

    @Test
    public void print_all_transactions() {
        List<Transaction> transactions = Arrays.asList(new Deposit("22/04/2019", 139));
        given(transactionRepository.allTransactions()).willReturn(transactions);

        account.printStatement();

        verify(statementPrinter).print(transactions);
    }
}
