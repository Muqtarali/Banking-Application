package domain;

import java.time.LocalDateTime;

public class Transaction {
    private String Id;
    private String accountNumber;
    private double amount;
    private LocalDateTime dateTime;
    private type type; // e.g., "deposit", "withdrawal", "transfer"

    public Transaction(String id, String accountNumber, double amount, LocalDateTime dateTime, type type) {
        Id = id;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.dateTime = dateTime;
        this.type = type;
    }
}
