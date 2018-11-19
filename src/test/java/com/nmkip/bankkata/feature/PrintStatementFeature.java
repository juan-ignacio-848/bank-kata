package com.nmkip.bankkata.feature;

import com.nmkip.bankkata.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {

    @Mock Console console;
    @Mock Clock clock;
    private Account account;

    @Before
    public void setUp() throws Exception {
        // Real repository (in memory repository) - Testing the system as a whole. Just mocking the external world
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        StatementPrinter statementPrinter = new StatementPrinter();
        account = new Account(transactionRepository, statementPrinter);
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

    // If Console is a mock and we are verifying those printLine, then why aren't we injecting the mock somewhere?
    // Why aren't we injecting the Console into Account?
    // We are not sure if Account will be calling the Console.
    // We don't know what we will have between the Account and the Console, so we are not going to inject it now.
    // We are going down to the unit level, start unit testing the Account and then we will see if the Console must
    // be injected into the Account or somewhere else.
}
