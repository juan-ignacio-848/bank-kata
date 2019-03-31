package com.nmkip.bankkata.steps;

import com.nmkip.bankkata.*;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AccountSteps {

    private Console console = mock(Console.class);

    private Clock clock = mock(Clock.class);

    private Account account;

    private void createAccount() {
        if (this.account == null) {
            this.account = new Account(clock, new TransactionRepository(), new StatementPrinter(console));
        }
    }

    @Given("^a client makes a deposit of ([0-9]*) on (.*)$")
    public void a_client_makes_a_deposit_of_$_on(int money, @Format("dd/MM/YYYY") String date) {
        createAccount();
        given(clock.todayAsString()).willReturn(date);
        account.deposit(money);
    }

    @Given("^a withdrawal of ([0-9]*) on (.*)$")
    public void a_withdrawal_of_$_on(Integer money, @Format("dd/MM/YYYY") String date) {
        createAccount();
        given(clock.todayAsString()).willReturn(date);
        account.withdraw(money);
    }

    @Given("^a deposit of ([0-9]*) on (.*)$")
    public void a_deposit_of_$_on(Integer money, @Format("dd/MM/YYYY") String date) {
        createAccount();
        given(clock.todayAsString()).willReturn(date);
        account.deposit(money);
    }

    @When("he prints his bank statement")
    public void he_prints_his_bank_statement() {
        createAccount();
        account.printStatement();
    }

    @Then("he should see:")
    public void he_should_see(String expected) {
        String[] split = expected.split("\n");
        InOrder inOrder = Mockito.inOrder(console);
        for (String str : split) {
            inOrder.verify(console).printLine(str);
        }
    }
}
