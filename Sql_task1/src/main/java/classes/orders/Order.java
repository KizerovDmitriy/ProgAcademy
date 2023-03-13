package classes.orders;


import classes.Id;

public class Order {
    @Id
    private int id;
    private Client client;
    private Product product;
    private Integer quantity;

    public Order(Client client, Product product, Integer quantity) {
        this.client = client;
        this.product = product;
        this.quantity = quantity;
    }

    public Order() {

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "client=" + client +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
