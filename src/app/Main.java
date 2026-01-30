package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("welcome to console Bank");
        boolean running=true;
        while(running){
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
            System.out.println("CHOOSE " + choice);
            switch (choice){
                case "0"-> running=false;
            }
        }
        System.out.println("thx");


    }
}
