package bank;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private BankAccount from;
    @ManyToOne
    private BankAccount to;
    @Column(nullable = false)
    private Double amountFunds;

    public Transaction(BankAccount from, BankAccount to, Double amountFunds) {
        this.from = from;
        this.to = to;
        this.amountFunds = amountFunds;
    }
    public Transaction(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BankAccount getFrom() {
        return from;
    }

    public void setFrom(BankAccount from) {
        this.from = from;
    }

    public BankAccount getTo() {
        return to;
    }

    public void setTo(BankAccount to) {
        this.to = to;
    }

    public Double getAmountFunds() {
        return amountFunds;
    }

    public void setAmountFunds(Double amountFunds) {
        this.amountFunds = amountFunds;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", amountFunds=" + amountFunds +
                '}';
    }
}
