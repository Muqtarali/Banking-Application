// `src/service/impl/BankServiceimpl.java`
package service.impl;

import domain.Account;
import repository.AccountRepository;
import service.BankService;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class BankServiceimpl implements BankService {
    private final AccountRepository accountRepository = new AccountRepository();

    @Override
    public String openaccount(String name, String id, String email, String accountType, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        String accountNumber = getAccountNumber();
        String customerId = UUID.randomUUID().toString();

        Account newAccount = new Account(accountNumber, customerId, initialDeposit, accountType);
        accountRepository.save(newAccount);

        return accountNumber;
    }

    @Override
    public List<Account> ListAccounts() {
        return accountRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .toList();
    }

    private String getAccountNumber() {
        int size = accountRepository.findAll().size() + 1;
        return String.format("AC%06d", size);
    }
}