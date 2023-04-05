package dao;

import bank.*;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

public class BankDao implements AutoCloseable {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bank");
    private final EntityManager em = emf.createEntityManager();

    public void addNewClient(Client client) {
        commit(client);
    }

    public void addCurrencyRates(Currency currencyType, Double rate) {
        CurrencyRate currencyRate = new CurrencyRate(currencyType, rate);
        commit(currencyRate);
    }

    public BankAccount getAccount(Client client, Currency currency) {
        return client.getBankAccounts()
                .stream()
                .filter(val -> val.getCurrency().equals(currency))
                .findFirst()
                .orElse(null);
    }

    public void transaction(BankAccount from, BankAccount to, Double amount, List<CurrencyRate> rates) {
        if (from.getAmount() < amount) {
            System.out.println("Insufficient funds on account " + from.getCurrency());
            return;
        }

        Currency fromCurrency = from.getCurrency();
        Currency toCurrency = to.getCurrency();

        double convertedAmount = getConvertedAmount(amount, rates, fromCurrency, toCurrency);

        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + convertedAmount);

        em.getTransaction().begin();
        em.merge(from);
        em.merge(to);
        em.getTransaction().commit();
        Transaction transaction = new Transaction(from, to, amount);
        commit(transaction);
        System.out.println("Transaction successful!");
    }

    private double getConvertedAmount(Double amount, List<CurrencyRate> rates, Currency fromCurrency, Currency toCurrency) {
        double convertedAmount = amount;
        if (fromCurrency != toCurrency) {
            if (fromCurrency.equals(Currency.UAH)) {
                convertedAmount /= getRate(rates, toCurrency);
            }
            if (fromCurrency.equals(Currency.USD)) {
                convertedAmount *= getRate(rates, Currency.USD);
                convertedAmount /= getRate(rates, toCurrency);
            }
            if (fromCurrency.equals(Currency.EUR)) {
                convertedAmount *= getRate(rates, Currency.EUR);
                convertedAmount /= getRate(rates, toCurrency);
            }
        }
        return convertedAmount;
    }

    private Double getRate(List<CurrencyRate> rates, Currency currency) {
        return rates.stream()
                .filter(rate -> rate.getCurrency() == currency)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rate not found for currency " + currency))
                .getExchangeRate();
    }

    public void totalCashOnAccount(Client client) {
        Double totalCash = 0.0;
        List<BankAccount> bankAccounts = client.getBankAccounts();
        Query query = em.createQuery("SELECT c FROM CurrencyRate c");
        List<CurrencyRate> currencyRates = query.getResultList();

        for (BankAccount account : bankAccounts) {
            Double balance = account.getAmount();
            Currency currency = account.getCurrency();
            Double rate = 1.0;
            for (CurrencyRate currencyRate : currencyRates) {
                if (currencyRate.getCurrency().equals(currency)) {
                    rate = currencyRate.getExchangeRate();
                    break;
                }
            }
            totalCash += balance * rate;
        }
        System.out.println("Client " + client.getClientName() + "total cash value is:  " + totalCash + " UAH");
    }

    public List<CurrencyRate> getCurrencyRates() {
        Query query = em.createQuery("SELECT c FROM CurrencyRate c");
        return (List<CurrencyRate>) query.getResultList();
    }

    public Client getClientByName(String name) {
        Query query = em.createQuery("SELECT c FROM Client c WHERE c.clientName = :name");
        query.setParameter("name", name);
        return (Client) query.getSingleResult();
    }

    public void deposit(Client client, Double amount, Currency currencyType) {
        List<BankAccount> bankAccounts = client.getBankAccounts();
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.getCurrency() == currencyType) {
                bankAccount.setAmount(bankAccount.getAmount() + amount);
                em.getTransaction().begin();
                em.merge(client);
                em.getTransaction().commit();
                break;
            }
        }
    }

    public void addNewBankAccount(Client client, Currency currency, Double amount) {
        Client client1 = em.find(Client.class, client.getId());
        BankAccount bankAccount = new BankAccount();
        bankAccount.setClient(client1);
        bankAccount.setAmount(amount);
        bankAccount.setCurrency(currency);
        bankAccount.setAccountNumber(generateRandomNumber(currency));
        List<BankAccount> bankAccounts = client1.getBankAccounts();
        bankAccounts.add(bankAccount);
        client1.setBankAccounts(bankAccounts);
        commit(bankAccount);
    }

    private <T> void commit(T t) {
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            System.out.println("The operation is successful!");
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public static String generateRandomNumber(Currency currency) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        if (currency.equals(Currency.UAH)) {
            sb.append("UAH");
        }
        if (currency.equals(Currency.USD)) {
            sb.append("USD");
        }
        if (currency.equals(Currency.EUR)) {
            sb.append("EUR");
        }
        for (int i = 0; i < 27; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    public void close() {
        em.close();
        emf.close();
    }
}