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

@WebServlet(name = "addorder", value = "addorder")
public class AddNewOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = ConnectionFactory.getConnection()){
            OrdersDAOImpl dao = new OrdersDAOImpl(connection);
            String clientParam = req.getParameter("client");
            String [] clientTmp = clientParam.split("-");
            String productParam = req.getParameter("product");
            String [] productTmp = productParam.split("-");
            Client client = new Client(clientTmp[0],clientTmp[1],clientTmp[2]);
            Product product = new Product(productTmp[0],Double.parseDouble(productTmp[1]),Integer.parseInt(productTmp[2]));
            client.setId(Integer.parseInt(clientTmp[3]));
            product.setId(Integer.parseInt(productTmp[3]));
            dao.createNewOrder(client,product,Integer.parseInt(req.getParameter("quantityorder")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("index.html");
    }
}
