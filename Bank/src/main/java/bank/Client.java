package bank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String clientName;
    @Column(nullable = false)
    @OneToMany(mappedBy = "client",cascade = CascadeType.REFRESH)
    private List<BankAccount> bankAccounts = new ArrayList<>(3);

    public Client(String clientName) {
        this.clientName = clientName;
    }

    public Client(String clientName, List<BankAccount> bankAccounts) {
        this.clientName = clientName;
        this.bankAccounts = bankAccounts;
    }
    public Client(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", bankAccounts=" + bankAccounts +
                '}';
    }
}