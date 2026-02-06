// `src/domain/Account.java`
package domain;

public class Account {
    private final String accountNumber;
    private final String customerId;
    private final String customerName;
    private double balance;
    private final String accountType;

    public Account(String accountNumber, String customerId, String customerName, double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.balance = balance;
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
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
