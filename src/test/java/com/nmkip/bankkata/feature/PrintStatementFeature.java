package com.nmkip.bankkata.feature;

import com.nmkip.bankkata.Account;
import com.nmkip.bankkata.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {

    @Mock
    private Console console;
    private Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account();
    }

    @Test
    public void print_statement_containing_all_transactions() {
        // Trigger
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(300);

        account.printStatement();

        // Side effect -- what I want to test
        // Order is important here.
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/06/2018 | 300.00 | 1200.00");
        inOrder.verify(console).printLine("10/06/2018 | -100.00 | 900.00");
        inOrder.verify(console).printLine("10/06/2018 | 1000.00 | 1000.00");
    }
}
