package classes.orders;

import classes.ConnectionFactory;
import dao.OrdersDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class MainOrders {
    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection("orders_db.properties");
             Scanner sc = new Scanner(System.in)) {

            OrdersDAOImpl dao = new OrdersDAOImpl(connection);

            while (true) {
                System.out.println("1: add client");
                System.out.println("2: add product");
                System.out.println("3: add new order");
                System.out.println("4: view all clients");
                System.out.println("5: view all products");
                System.out.println("6: view all orders");
                System.out.print("Input your choice --> ");
                String choice = sc.nextLine();

                switch (choice) {
                    case "1":
                        System.out.println("Input first name:");
                        String firstName = sc.nextLine();
                        System.out.println("Input last name:");
                        String lastName = sc.nextLine();
                        System.out.println("Input phone number:");
                        String phoneNumber = sc.nextLine();
                        dao.addClient(firstName, lastName, phoneNumber);
                        break;
                    case "2":
                        System.out.println("Input product name:");
                        String productName = sc.nextLine();
                        System.out.println("Input price:");
                        String price = sc.nextLine();
                        System.out.println("Input quantity:");
                        String quantity = sc.nextLine();
                        dao.addNewProduct(productName, Double.parseDouble(price), Integer.parseInt(quantity));
                        break;
                    case "3":
                        System.out.println("Input order details:");
                        System.out.println("Input client name:");
                        String name = sc.nextLine();
                        System.out.println("Input client surname:");
                        String surname = sc.nextLine();
                        System.out.println("Input client phone number:");
                        String phoneNum = sc.nextLine();
                        Client client = new Client(name, surname, phoneNum);
                        if (dao.checkClient(client) != null) {
                            dao.addClient(client.getFirst_name(), client.getLast_name(), client.getPhone_number());
                        }
                        System.out.println("Input name of product:");
                        String nameOfProduct = sc.nextLine();
                        Product product = dao.checkProduct(nameOfProduct);
                        if (product == null) {
                            System.out.println("Product is not found!");
                        } else {
                            System.out.println("Input quantity:");
                            int quantityOfProduct = sc.nextInt();
                            dao.createNewOrder(dao.getClientByNPhoneNumber(client.getPhone_number()), product, quantityOfProduct);
                        }
                        break;
                    case "4":
                        Map<Integer, Client> clientList = dao.viewAllClients();
                        for (Map.Entry<Integer, Client> clientEntry : clientList.entrySet()) {
                            System.out.println(clientEntry.getValue());
                        }
                        break;
                    case "5":
                        Map<Integer, Product> productList = dao.viewAllProducts();
                        for (Map.Entry<Integer, Product> productEntry : productList.entrySet()) {
                            System.out.println(productEntry.getValue());
                        }
                        break;
                    case "6":
                        Map<Integer, Order> orderList = dao.viewAllOrders();
                        for (Map.Entry<Integer, Order> order : orderList.entrySet()) {
                            System.out.println(order.getValue());
                        }
                        break;
                    default:
                        return;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}