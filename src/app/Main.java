// `src/app/Main.java`
package app;

import domain.Account;
import domain.Transaction;
import service.BankService;
import service.impl.BankServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService service = new BankServiceImpl();

        System.out.println("welcome to console Bank");
        boolean running = true;

        while (running) {
            System.out.println("""
                1) Open Account
                2) Deposit
                3) Withdraw
                4) Transfer
                5) Account Statement
                6) List Accounts
                7) Search Account By Customer Name
                8) Exit
                """);

            System.out.println("CHOOSE");
            String choice = sc.next().trim();

            switch (choice) {
                case "1" -> openAccount(sc, service);
                case "2" -> deposit(sc, service);
                case "3" -> withdraw(sc, service);
                case "4" -> transfer(sc, service);
                case "5" -> accountStatement(sc, service);
                case "6" -> listAccounts(service);
                case "7" -> searchAccountByCustomerName(sc, service);
                case "8" -> running = false;
                default -> System.out.println("Invalid choice");
            }
        }

        System.out.println("thx");
    }

    public static void openAccount(Scanner sc, BankService service) {
        System.out.println("Customer Name:");
        String name = sc.next().trim();

        System.out.println("Customer ID:");
        String id = sc.next().trim();

        System.out.println("Customer Email:");
        String email = sc.next().trim();

        System.out.println("Account type (Savings/Current):");
        String accountType = sc.next().trim();

        System.out.println("Initial deposit:");
        double initialDeposit = sc.nextDouble();

        String accountNumber = service.openaccount(name, id, email, accountType, initialDeposit);
        System.out.println("Account created. Account Number: " + accountNumber);
    }

    public static void deposit(Scanner sc, BankService service) {
        System.out.println("Account Number:");
        String accountNumber = sc.next().trim();

        System.out.println("Amount:");
        double amount = sc.nextDouble();

        boolean success = service.deposit(accountNumber, amount);
        System.out.println(success ? "Deposit successful" : "Deposit failed");
    }

    public static void withdraw(Scanner sc, BankService service) {
        System.out.println("Account Number:");
        String accountNumber = sc.next().trim();

        System.out.println("Amount:");
        double amount = sc.nextDouble();

        sc.nextLine(); // consume leftover newline
        System.out.println("Note:");
        String note = sc.nextLine().trim();

        boolean success = service.withdraw(accountNumber, amount, note);
        System.out.println(success ? "Withdrawal successful" : "Withdrawal failed");
    }

    public static void transfer(Scanner sc, BankService service) {
        System.out.println("From account:");
        String fromAccountNumber = sc.next().trim();

        System.out.println("To account:");
        String toAccountNumber = sc.next().trim();

        System.out.println("Amount:");
        double amount = sc.nextDouble();

        sc.nextLine(); // consume leftover newline
        System.out.println("Note:");
        String note = sc.nextLine().trim();

        boolean success = service.transfer(fromAccountNumber, toAccountNumber, amount, note);
        System.out.println(success ? "Transfer is complete" : "Transfer not complete yet");
    }

    public static void accountStatement(Scanner sc, BankService service) {
        System.out.println("Account Number:");
        String accountNumber = sc.next().trim();

        List<Transaction> statement = service.accountStatement(accountNumber);

        if (statement == null || statement.isEmpty()) {
            System.out.println("No transactions found for account: " + accountNumber);
            return;
        }

        System.out.println("Statement for account: " + accountNumber);
        for (Transaction tx : statement) {
            System.out.println(tx);
        }
    }

    public static void listAccounts(BankService service) {
        List<Account> accounts = service.ListAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (Account account : accounts) {
            System.out.println(
                    "Account Number: " + account.getAccountNumber()
                            + " | Customer ID: " + account.getCustomerId()
                            + " | Balance: " + account.getBalance()
                            + " | Account Type: " + account.getAccountType()
            );
        }
    }

    public static void searchAccountByCustomerName(Scanner sc, BankService service) {
        sc.nextLine(); // consume leftover newline if previous input used nextDouble()
        System.out.println("Enter your name:");
        String name = sc.nextLine().trim();

        List<Account> matches = service.searchAccounts(name);

        if (matches.isEmpty()) {
            System.out.println("No matching accounts found.");
            return;
        }

        for (Account account : matches) {
            System.out.println(
                    "Account Number: " + account.getAccountNumber()
                            + " | Customer ID: " + account.getCustomerId()
                            + " | Balance: " + account.getBalance()
                            + " | Account Type: " + account.getAccountType()
            );
        }
    }
}
