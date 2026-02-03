// `src/service/BankService.java`
package service;

import domain.Account;

import java.util.List;

public interface BankService {
    String openaccount(String name, String id, String email, String accountType, double initialDeposit);

    List<Account> ListAccounts();
}