// src/service/impl/BankServiceImpl.java
package service.impl;

import domain.Account;
import domain.Transaction;
import domain.Type;
import repository.AccountRepository;
import repository.TransactionRepository;
import service.BankService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository = new AccountRepository();
    private final TransactionRepository transactionRepository = new TransactionRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();

    @Override
    public String openaccount(String name, String id, String email, String accountType, double initialDeposit) {
        if (initialDeposit < 0) throw new IllegalArgumentException("Initial deposit cannot be negative");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or empty");

        String accountNumber = getAccountNumber();
        String customerId = (id != null && !id.isBlank()) ? id : UUID.randomUUID().toString();

        Account newAccount = new Account(accountNumber, customerId, name, initialDeposit, accountType);
        accountRepository.save(newAccount);
        return accountNumber;
    }

    @Override
    public List<Account> ListAccounts() {
        return accountRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    private String getAccountNumber() {
        int size = accountRepository.findAll().size() + 1;
        return String.format("AC%06d", size);
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        for (Account account : accountRepository.findAll()) {
            if (accountNumber.equals(account.getAccountNumber())) {
                account.setBalance(account.getBalance() + amount);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean withdraw(String accountNumber, double amount, String note) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be null or blank");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        for (Account account : accountRepository.findAll()) {
            if (accountNumber.equals(account.getAccountNumber())) {
                if (account.getBalance() < amount) {
                    throw new IllegalArgumentException("Insufficient balance");
                }
                account.setBalance(account.getBalance() - amount);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount, String note) {
        if (fromAccountNumber == null || fromAccountNumber.isBlank()
                || toAccountNumber == null || toAccountNumber.isBlank()) {
            throw new IllegalArgumentException("Account numbers cannot be null or blank");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        Account fromAccount = null;
        Account toAccount = null;

        for (Account account : accountRepository.findAll()) {
            if (fromAccountNumber.equals(account.getAccountNumber())) fromAccount = account;
            if (toAccountNumber.equals(account.getAccountNumber())) toAccount = account;
        }

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("One or both accounts not found");
        }
        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance in the source account");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        transactionRepository.save(new Transaction(fromAccountNumber, -amount, note, LocalDateTime.now(), Type.TRANSFER_OUT));
        transactionRepository.save(new Transaction(toAccountNumber, amount, note, LocalDateTime.now(), Type.TRANSFER_IN));

        return true;
    }

    @Override
    public List<Transaction> accountStatement(String accountNumber) {
        return transactionRepository.findAll()
                .stream()
                .filter(tx -> accountNumber.equals(tx.getAccountNumber()))
                .sorted(Comparator.comparing(Transaction::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccounts(String name) {
        if (name == null || name.isBlank()) {
            return List.of();
        }

        String q = name.toLowerCase();

        return accountRepository.findAll().stream()
                .filter(a ->
                        (a.getAccountNumber() != null && a.getAccountNumber().toLowerCase().contains(q)) ||
                                (a.getCustomerId() != null && a.getCustomerId().toLowerCase().contains(q))
                )
                .collect(Collectors.toList());
    }
}
