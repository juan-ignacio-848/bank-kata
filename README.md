#### Problem description - Bank kata

Create a simple bank application with the following features:

- Deposit into Account
- Withdraw from an Account
- Print a bank statement to the console

#### Acceptance criteria

Statement should have transactions in the following format:

\> DATE | AMOUNT | BALANCE  
\> 10/06/2018 | 300 | 1200  
\> 08/06/2018 | -100 | 900  
\> 10/05/2018 | 1000 | 1000  

#### Starting point and constraints

1. Start with a class the following structure:

```java
public class Account {
  public void deposit(int amount);
  public void withdraw(int amount);
  public void printStatement();
}
```

2. You are not allowed to add any other public method to this class.

3. Use Strings and Integers for dates and amounts (keep it simple)

4. Don't worry about spacing in the statement printed on the console.

 
