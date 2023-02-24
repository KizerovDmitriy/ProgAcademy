package classes;

import annotations.Save;

public class Test {
    @Save
    private BankClient bankClient;
    @Save
    private String test;

    public Test(BankClient bankClient, String test) {
        this.bankClient = bankClient;
        this.test = test;
    }
    public Test(){}

    @Override
    public String toString() {
        return "Test{" +
                "bankClient=" + bankClient +
                ", test='" + test + '\'' +
                '}';
    }
}
