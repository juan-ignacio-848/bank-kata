package com.nmkip.bankkata.unit;

import com.nmkip.bankkata.Account;
import com.nmkip.bankkata.StatementPrinter;
import com.nmkip.bankkata.Transaction;
import com.nmkip.bankkata.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {

    @Mock
    TransactionRepository transactionRepository;
    @Mock
    StatementPrinter statementPrinter;

    private Account account;

    @Before
    public void setUp() {
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    public void deposit_a_transaction() {
        account.deposit(100);

        verify(transactionRepository).addDeposit(100);
    }

    @Test
    public void withdraw_a_transaction() {
        account.withdraw(100);

        verify(transactionRepository).addWithdrawal(100);
    }

    @Test
    public void print_a_transaction_statement() {
        List<Transaction> transactions = asList(new Transaction("12/03/2018", 100));
        given(transactionRepository.allTransactions()).willReturn(transactions);

        account.printStatement();

        verify(statementPrinter).printStatement(transactions);
    }
}