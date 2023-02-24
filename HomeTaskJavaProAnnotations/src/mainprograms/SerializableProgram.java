package mainprograms;

import classes.BankClient;
import classes.Saver;
import classes.Client;

public class SerializableProgram {
    public static void main(String[] args) {
        Client client = new Client("Dmytro","Kizerov","32","0666334509");
        BankClient bankClient = new BankClient(client,"1411990","1500.22");
        Saver saver = new Saver();
        saver.serializeObject(bankClient,args[0]);
        BankClient bankClient1 = new BankClient();
        saver.deserializeObject(bankClient1,args[0]);
        System.out.println(bankClient1);
    }
}