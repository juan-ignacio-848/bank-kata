package com.nmkip.bankkata.feature;

import com.nmkip.bankkata.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {

    @Mock
    private Console console;

    @Mock
    private Clock clock;

    private Account account;

    @Before
    public void setUp() {
        TransactionRepository transactionRepository = new TransactionRepository();
        StatementPrinter statementPrinter = new StatementPrinter(console);
        account = new Account(clock, transactionRepository, statementPrinter);
    }

    @Test
    public void print_a_statement() {
        given(clock.todayAsString()).willReturn(
                "10/05/2018",
                "08/06/2018",
                "10/06/2018"
        );

        account.deposit(1000);
        account.withdraw(100);
        account.deposit(300);

        account.printStatement();

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/06/2018 | 300.00 | 1200.00");
        inOrder.verify(console).printLine("08/06/2018 | -100.00 | 900.00");
        inOrder.verify(console).printLine("10/05/2018 | 1000.00 | 1000.00");
    }

}
