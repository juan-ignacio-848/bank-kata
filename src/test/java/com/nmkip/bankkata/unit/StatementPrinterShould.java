package com.nmkip.bankkata.unit;

import com.nmkip.bankkata.Console;
import com.nmkip.bankkata.StatementPrinter;
import com.nmkip.bankkata.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

    private static final List<Transaction> NO_TRANSACTIONS = EMPTY_LIST;

    @Mock Console console;
    private StatementPrinter statementPrinter;

    @Before
    public void setUp() {
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    public void always_print_the_header() {
        statementPrinter.print(NO_TRANSACTIONS);

        verify(console).printLine("DATE | AMOUNT | BALANCE");
    }

    @Test
    public void print_transactions_in_reverse_chronological_order() {
        List<Transaction> transactions = transactionsContaining(
                deposit("02/05/2018", 1000),
                withdrawal("10/06/2018", 100),
                deposit("12/07/2018", 300)
        );

        statementPrinter.print(transactions);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("12/07/2018 | 300.00 | 1200.00");
        inOrder.verify(console).printLine("10/06/2018 | -100.00 | 900.00");
        inOrder.verify(console).printLine("02/05/2018 | 1000.00 | 1000.00");

    }

    private List<Transaction> transactionsContaining(Transaction... transactions) {
        return asList(transactions);
    }

    private Transaction withdrawal(String date, int amount) {
        return new Transaction(date, -amount);
    }

    private Transaction deposit(String date, int amount) {
        return new Transaction(date, amount);
    }
}