// src/repository/AccountRepository.java
package repository;

import domain.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepository {

    private final Map<String, Account> accountByNumber = new HashMap<>();

    public void save(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("account cannot be null");
        }
        accountByNumber.put(account.getAccountNumber(), account);
    }

    public Account findByAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("accountNumber cannot be null or blank");
        }
        return accountByNumber.get(accountNumber);
    }

    public List<Account> findAll() {
        return new ArrayList<>(accountByNumber.values());
    }
}
