package com.nmkip.bankkata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

    private static final List NO_TRANSACTIONS = Collections.EMPTY_LIST;

    @Mock
    private Console console;

    private StatementPrinter statementPrinter;

    @Before
    public void setUp() throws Exception {
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    public void always_print_the_header() {
        statementPrinter.print(NO_TRANSACTIONS);

        verify(console).printLine("DATE | AMOUNT | BALANCE");
    }

    @Test
    public void print_one_deposit_statement() {
        List<Transaction> transactions = asList(new Deposit("10/06/2018", 300));

        statementPrinter.print(transactions);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/06/2018 | 300.00 | 300.00");
    }

    @Test
    public void print_two_deposit_statement() {
        List<Transaction> transactions = asList(
                new Deposit("10/05/2018", 1000),
                new Deposit("10/06/2018", 300));

        statementPrinter.print(transactions);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/06/2018 | 300.00 | 1300.00");
        inOrder.verify(console).printLine("10/05/2018 | 1000.00 | 1000.00");
    }

    @Test
    public void print_a_withdrawal_statement() {
        List<Transaction> transactions = asList(
                new Deposit("10/05/2018", 1000),
                new Withdrawal("10/06/2018", 300));

        statementPrinter.print(transactions);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/06/2018 | -300.00 | 700.00");
        inOrder.verify(console).printLine("10/05/2018 | 1000.00 | 1000.00");
    }
}