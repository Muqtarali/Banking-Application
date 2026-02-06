// `src/service/BankService.java`
package service;

import domain.Account;
import domain.Transaction;

import java.util.List;

public interface BankService {
    String openaccount(String name, String id, String email, String accountType, double initialDeposit);

    List<Account> ListAccounts();

    boolean deposit(String accountNumber, double amount);

    boolean withdraw(String accountNumber, double amount, String note);

        boolean transfer(String fromAccountNumber, String toAccountNumber, double amount, String note);

        List<Transaction> accountStatement(String accountNumber);
        List<Account> searchAccounts(String name);

}
