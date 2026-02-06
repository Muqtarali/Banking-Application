// `src/domain/Transaction.java`
package domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final String id;
    private final String accountNumber;
    private final double amount;
    private final String note;
    private final LocalDateTime createdAt;
    private final Type type;

    // Existing 3-arg constructor (keep for backward compatibility)
    public Transaction(String accountNumber, double amount, String note) {
        this(accountNumber, amount, note, LocalDateTime.now(), Type.DEPOSIT);
    }

    // New 5-arg constructor (matches your usage)
    public Transaction(String accountNumber,
                       double amount,
                       String note,
                       LocalDateTime createdAt,
                       Type type) {
        this.id = UUID.randomUUID().toString();
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.note = note;
        this.createdAt = createdAt;
        this.type = type;
    }

    public String getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public double getAmount() { return amount; }
    public String getNote() { return note; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Type getType() { return type; }
}
