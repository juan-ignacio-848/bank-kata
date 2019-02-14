package com.nmkip.bankkata.unit;

import com.nmkip.bankkata.Console;
import com.nmkip.bankkata.StatementPrinter;
import com.nmkip.bankkata.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

    @Mock
    Console console;

    @Test
    public void print_statement_header() {
        StatementPrinter statementPrinter = new StatementPrinter(console);
        statementPrinter.printStatement(EMPTY_LIST);

        verify(console).printLine("DATE | AMOUNT | BALANCE");
    }


    @Test
    public void print_transactions_in_reverse_chronological_order() {
        StatementPrinter statementPrinter = new StatementPrinter(console);

        statementPrinter.printStatement(asList(
                deposit("01/11/2018", 1000),
                withdrawal("02/11/2018", 300),
                deposit("21/11/2018", 100)
                )
        );

        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("21/11/2018 | 100.00 | 800.00");
        inOrder.verify(console).printLine("02/11/2018 | -300.00 | 700.00");
        inOrder.verify(console).printLine("01/11/2018 | 1000.00 | 1000.00");
    }

    private Transaction withdrawal(String date, int amount) {
        return new Transaction(date, -amount);
    }

    private Transaction deposit(String date, int amount) {
        return new Transaction(date, amount);
    }
}