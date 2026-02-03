// `src/domain/Account.java`
package domain;

public class Account {
    private final String accountNumber;
    private final String customerId;
    private double balance;
    private final String accountType;

    public Account(String accountNumber, String customerId, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}