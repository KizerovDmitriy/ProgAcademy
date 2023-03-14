package dao;

import classes.Client;
import classes.Order;
import classes.Product;

import java.util.Map;

public interface OrdersDAO {
    void addClient(String first_name, String last_name, String phone_number);

    void addNewProduct(String name, Double price, Integer quantity);

    void createNewOrder(Client client, Product product, Integer quantity);

    Map<Integer, Client> viewAllClients();

    Map<Integer, Product> viewAllProducts();

    Map<Integer, Order> viewAllOrders();
}
