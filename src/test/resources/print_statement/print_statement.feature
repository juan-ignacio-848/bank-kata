Feature: Print a bank statement

  Scenario: Print transaction statements in reverse chronological order
    Given a client makes a deposit of 1000 on 10/05/2018
    And a withdrawal of 100 on 08/06/2018
    And a deposit of 300 on 10/06/2018
    When he prints his bank statement
    Then he should see:
                        """
                        DATE | AMOUNT | BALANCE
                        10/06/2018 | 300.00 | 1200.00
                        08/06/2018 | -100.00 | 900.00
                        10/05/2018 | 1000.00 | 1000.00
                        """