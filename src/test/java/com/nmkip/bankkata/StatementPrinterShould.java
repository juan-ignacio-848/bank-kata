package com.nmkip.bankkata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

    private static final String YESTERDAY = "19/05/2019";
    private static final String NOW = "20/05/2019";
    private static final List<Transaction> NO_TRANSACTIONS = emptyList();

    @Mock
    private Console console;
    private StatementPrinter statementPrinter;

    @Before
    public void setUp() throws Exception {
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    public void always_print_statement_header() {
        statementPrinter.print(NO_TRANSACTIONS);

        verify(console).printLine("DATE | AMOUNT | BALANCE");
    }

    @Test
    public void print_statement_transactions_in_reverse_chronological_order() {
        List<Transaction> transactions = transactions(
            new Deposit("10/05/2018", 1000),
            new Withdrawal("08/06/2018", 100),
            new Deposit("10/06/2018", 300)
        );

        statementPrinter.print(transactions);

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/06/2018 | 300.00 | 1200.00");
        inOrder.verify(console).printLine("08/06/2018 | -100.00 | 900.00");
        inOrder.verify(console).printLine("10/05/2018 | 1000.00 | 1000.00");
    }

    private List<Transaction> transactions(Transaction... transactions) {
        return Stream.of(transactions).collect(Collectors.toList());
    }

}