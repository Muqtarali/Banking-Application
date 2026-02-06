// `src/repository/TransactionRepository.java`
package repository;

import domain.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    public void save(Transaction tx) {
        if (tx == null) throw new IllegalArgumentException("Transaction cannot be null");
        transactions.add(tx);
    }

    public List<Transaction> findAll() {
        return Collections.unmodifiableList(transactions);
    }
    public List<Transaction> findByAccount(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank");
        }

        List<Transaction> result = new ArrayList<>();
        for (Transaction tx : transactions) {
            if (accountNumber.equals(tx.getAccountNumber())) {
                result.add(tx);
            }
        }
        return result;
    }
}


