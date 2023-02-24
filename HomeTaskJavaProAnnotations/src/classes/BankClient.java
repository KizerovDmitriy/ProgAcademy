package classes;

import annotations.Save;

public class BankClient {
    @Save
    private Client client;
    private String password;
    @Save
    private String balance;

    public BankClient(Client client, String password, String balance) {
        this.client = client;
        this.password = password;
        this.balance = balance;
    }
    public BankClient(){}

    @Override
    public String toString() {
        return "BankClient{" +
                "client=" + client +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
