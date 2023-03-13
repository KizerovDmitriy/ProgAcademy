package dao;

import classes.orders.Client;
import classes.orders.Order;
import classes.orders.Product;

import java.util.Map;

public interface OrdersDAO {
    void addClient(String first_name, String last_name, String phone_number);

    void addNewProduct(String name, Double price, Integer quantity);

    void createNewOrder(Client client, Product product, Integer quantity);

    Map<Integer, Client> viewAllClients();

    Map<Integer, Product> viewAllProducts();

    Map<Integer, Order> viewAllOrders();
}