package mainprograms;

import classes.BankClient;
import classes.Saver;
import classes.Client;
import classes.Test;


public class SerializableProgram {
    public static void main(String[] args) {
        Client client = new Client("Dmytro", "Kizerov", 32, "0666334509");
        BankClient bankClient = new BankClient(client, "1411990", "1500.22");
        Saver saver = new Saver();

        saver.serializeObject(bankClient, args[0]);
        BankClient bankClient1 = new BankClient();
        saver.deserializeObject(bankClient1, args[0]);
        System.out.println(bankClient1);


        Test test = new Test(bankClient, "TEST");
        saver.serializeObject(test, args[0]);
        Test test1 = new Test();
        saver.deserializeObject(test1, args[0]);
        System.out.println(test1);

        saver.serializeObject(client, args[0]);
        Client client1 = new Client();
        saver.deserializeObject(client1, args[0]);
        System.out.println(client1);
    }
}