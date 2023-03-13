package dao;

import classes.orders.Client;
import classes.orders.Order;
import classes.orders.Product;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class OrdersDAOImpl implements OrdersDAO {
    private final Connection connection;

    public OrdersDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addClient(String first_name, String last_name, String phone_number) {
        Map<Integer, Client> clientMap = viewAllClients();
        for (Map.Entry<Integer, Client> entry : clientMap.entrySet()) {
            if (entry.getValue().getPhone_number().equals(phone_number)) {
                System.out.println("Client already exist in base");
                return;
            }
        }
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO clients (first_name,last_name,phone_number)" +
                "VALUES (?,?,?)")) {
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, phone_number);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addNewProduct(String name, Double price, Integer quantity) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO goods (product_name,price,quantity)" +
                "VALUES (?,?,?)")) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createNewOrder(Client client, Product product, Integer quantity) {

        if (product.getQuantity() < quantity) {
            System.out.println("Insufficient quantity to order!");
            System.out.println("Available to order - " + product.getQuantity());
            return;
        }

        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO orders (client_id,product_id,quantity)" +
                "VALUES (?,?,?)")) {

            Map<Integer, Client> clientList = viewAllClients();
            Map<Integer, Product> productList = viewAllProducts();

            if (clientList.containsKey(client.getId()) && productList.containsKey(product.getId())) {

                ps.setInt(1, client.getId());
                ps.setInt(2, product.getId());
                ps.setInt(3, quantity);
                ps.executeUpdate();

                try (PreparedStatement changeQuantity = connection.prepareStatement("UPDATE goods SET quantity = ? WHERE product_id = ?")) {
                    changeQuantity.setInt(1, (product.getQuantity() - quantity));
                    changeQuantity.setInt(2, product.getId());
                    changeQuantity.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Integer, Client> viewAllClients() {
        Map<Integer, Client> result = new HashMap<>();
        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM clients")) {
                while (rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getInt(1));
                    client.setFirst_name(rs.getString(2));
                    client.setLast_name(rs.getString(3));
                    client.setPhone_number(rs.getString(4));
                    result.put(client.getId(), client);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Map<Integer, Product> viewAllProducts() {
        Map<Integer, Product> result = new HashMap<>();
        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM goods")) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt(1));
                    product.setName(rs.getString(2));
                    product.setPrice(rs.getDouble(3));
                    product.setQuantity(rs.getInt(4));
                    result.put(product.getId(), product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Map<Integer, Order> viewAllOrders() {
        Map<Integer, Order> result = new HashMap<>();
        Map<Integer, Client> clientMap = viewAllClients();
        Map<Integer, Product> productMap = viewAllProducts();

        try (Statement st = connection.createStatement()) {
            try (ResultSet rs = st.executeQuery("SELECT * FROM orders")) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt(1));
                    order.setClient(clientMap.get(rs.getInt(2)));
                    order.setProduct(productMap.get(rs.getInt(3)));
                    order.setQuantity(rs.getInt(4));
                    result.put(order.getId(), order);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Product checkProduct(String name) {
        Map<Integer, Product> tmp = viewAllProducts();
        for (Map.Entry<Integer, Product> product : tmp.entrySet()) {
            if (product.getValue().getName().equalsIgnoreCase(name)) {
                return product.getValue();
            }
        }
        return null;
    }

    public Client checkClient(Client client) {
        Map<Integer, Client> tmp = viewAllClients();
        for (Map.Entry<Integer, Client> clients : tmp.entrySet()) {
            if (clients.getValue().getPhone_number().equalsIgnoreCase(client.getPhone_number())) {
                System.out.println("Client already exist!");
                return null;
            }
        }
        return client;
    }

    public Client getClientByNPhoneNumber(String phoneNumber) {
        Map<Integer, Client> tmp = viewAllClients();
        for (Map.Entry<Integer, Client> client : tmp.entrySet()) {
            if (client.getValue().getPhone_number().equalsIgnoreCase(phoneNumber))
                return client.getValue();
        }
        return null;
    }
}