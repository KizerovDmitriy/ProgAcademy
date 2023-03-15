package com.example.servletdb;

import classes.Client;
import classes.ConnectionFactory;
import classes.Product;
import dao.OrdersDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.StringJoiner;

@WebServlet(name = "chooseClients", value = "chooseClients")
public class ChooseClientsAndProductServlet extends HttpServlet {
    private Map<Integer, Client> clients;
    private Map<Integer, Product> products;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            OrdersDAOImpl dao = new OrdersDAOImpl(connection);
            clients = dao.viewAllClients();
            products = dao.viewAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("chooseclients", checkBoxForClients());
        req.setAttribute("chooseProduct", checkBoxForProduct());
        req.getRequestDispatcher("chooseClient.jsp").forward(req, resp);
    }

    private String checkBoxForProduct() {
        StringBuilder sb = new StringBuilder();
        StringJoiner sj = new StringJoiner("-");
        for (Map.Entry<Integer, Product> product : products.entrySet()) {
            sb.append("<input type=\"radio\" id=\"product\" name=\"product\" value=\"")
                    .append(product.getValue().getName() + "-" + product.getValue().getPrice() + "-" + product.getValue().getQuantity() + "-" + product.getValue().getId())
                    .append("\">")
                    .append("<label for=\"Product\">")
                    .append(product.getValue().getName())
                    .append(" ")
                    .append(product.getValue().getPrice())
                    .append("</label><br>")
                    .append("<br>");
        }
        return sb.toString();
    }

    private String checkBoxForClients() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Client> client : clients.entrySet()) {
            sb.append("<input type=\"radio\" id=\"client\" name=\"client\" value=\"")
                    .append(client.getValue().getFirst_name() + "-" + client.getValue().getLast_name() + "-" + client.getValue().getPhone_number() + "-" + client.getValue().getId())
                    .append("\">")
                    .append("<label for=\"Client\">")
                    .append(client.getValue().getFirst_name())
                    .append(" ")
                    .append(client.getValue().getLast_name())
                    .append("</label><br>")
                    .append("<br>");
        }
        return sb.toString();
    }
}