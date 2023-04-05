package app;

import bank.BankAccount;
import bank.Client;
import bank.Currency;
import dao.BankDao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (BankDao dao = new BankDao()) {
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    String choice = showMenu(scanner);
                    switch (choice) {
                        case "1" -> addNewClientAndNewAccount(dao, scanner);
                        case "2" -> addNewAccountToClient(dao, scanner);
                        case "3" -> depositByCurrencyType(dao, scanner);
                        case "4" -> Transaction(dao, scanner);
                        case "5" -> dao.totalCashOnAccount(getClientByName(dao, scanner));
                        case "6" -> addCurrencyRates(dao);
                        default -> {
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void Transaction(BankDao dao, Scanner scanner) {
        Client client = getClientByName(dao, scanner);
        System.out.println("From which account to which funds need to be transferred?");
        System.out.println("From:");
        BankAccount from = chooseCurrency(dao, scanner, client);
        System.out.println("To:");
        BankAccount to = chooseCurrency(dao, scanner, client);
        System.out.println("Enter the amount of funds:");
        String amount = scanner.nextLine();
        dao.transaction(from, to, Double.parseDouble(amount),dao.getCurrencyRates());
    }

    private static BankAccount chooseCurrency(BankDao dao, Scanner scanner, Client client) {
        BankAccount from = null;
        System.out.println("""
                1: UAH
                2: USD
                3: EUR""");
        String choiceFrom = scanner.nextLine();
        switch (choiceFrom) {
            case "1" -> from = dao.getAccount(client, Currency.UAH);
            case "2" -> from = dao.getAccount(client, Currency.USD);
            case "3" -> from = dao.getAccount(client, Currency.EUR);
        }
        return from;
    }

    private static void addCurrencyRates(BankDao dao) {
        dao.addCurrencyRates(Currency.UAH, 1.0);
        dao.addCurrencyRates(Currency.USD, 37.5);
        dao.addCurrencyRates(Currency.EUR, 39.3);
    }

    private static void depositByCurrencyType(BankDao dao, Scanner scanner) {
        Client client = getClientByName(dao, scanner);
        System.out.println("""
                1: UAH
                2: USD
                3: EUR
                Enter currency type to deposit:""");
        String currencyType = scanner.nextLine();
        System.out.println("Enter the amount of money to deposit account:");
        String amount = scanner.nextLine();
        switch (currencyType) {
            case "1" -> dao.deposit(client, Double.parseDouble(amount), Currency.UAH);
            case "2" -> dao.deposit(client, Double.parseDouble(amount), Currency.USD);
            case "3" -> dao.deposit(client, Double.parseDouble(amount), Currency.EUR);
        }
    }

    private static void addNewAccountToClient(BankDao dao, Scanner scanner) {
        Client client = getClientByName(dao, scanner);
        choseTypeOfBankAccount(dao, scanner, client);
    }

    private static Client getClientByName(BankDao dao, Scanner scanner) {
        System.out.println("Input client name:");
        String clientName = scanner.nextLine();
        return dao.getClientByName(clientName);
    }

    private static void addNewClientAndNewAccount(BankDao dao, Scanner scanner) {
        System.out.println("Input client name:");
        String clientName = scanner.nextLine();
        Client client = new Client(clientName);
        dao.addNewClient(client);
        System.out.println("You want open to this client " + client.getClientName() + " new bank account: Y/N?");
        String choice2 = scanner.nextLine();
        addNewAccount(dao, scanner, client, choice2);
    }

    private static void addNewAccount(BankDao dao, Scanner scanner, Client client, String choice2) {
        if (choice2.equalsIgnoreCase("N")) {
            return;
        }
        choseTypeOfBankAccount(dao, scanner, client);
    }

    private static void choseTypeOfBankAccount(BankDao dao, Scanner scanner, Client client) {
        System.out.println("Chose currency type of this account:");
        System.out.println("1: USD");
        System.out.println("2: EUR");
        System.out.println("3: UAH");
        String currencyType = scanner.nextLine();
        switch (currencyType) {
            case "1" -> {
                System.out.println("what amount do you want to deposit in your account?");
                String amount = scanner.nextLine();
                dao.addNewBankAccount(client, Currency.USD, Double.parseDouble(amount));
            }
            case "2" -> {
                System.out.println("what amount do you want to deposit in your account?");
                String amount = scanner.nextLine();
                dao.addNewBankAccount(client, Currency.EUR, Double.parseDouble(amount));
            }
            case "3" -> {
                System.out.println("what amount do you want to deposit in your account?");
                String amount = scanner.nextLine();
                dao.addNewBankAccount(client, Currency.UAH, Double.parseDouble(amount));
            }
        }
    }

    private static String showMenu(Scanner scanner) {
        System.out.println("1: add new client");
        System.out.println("2: add new bank account to client");
        System.out.println("3: deposit found to account");
        System.out.println("4: transfer money");
        System.out.println("5: get all cash in UAH");
        System.out.println("6: add currency rates");
        System.out.println("Input your choice:");
        return scanner.nextLine();
    }
}