package com.example.servletdb;

import classes.ConnectionFactory;
import classes.Order;
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

@WebServlet(name = "vieworders", value = "vieworders")
public class OrdersViewServlet extends HttpServlet {
    private Map<Integer, Order> orders;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            OrdersDAOImpl dao = new OrdersDAOImpl(connection);
            orders = dao.viewAllOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("orders", viewOrders());
        req.getRequestDispatcher("/ordersview.jsp").forward(req, resp);
    }

    private String viewOrders() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Order> order : orders.entrySet()) {
            sb.append("<tr><td>")
                    .append(order.getValue().getClient().getFirst_name())
                    .append(" ")
                    .append(order.getValue().getClient().getLast_name())
                    .append("</td><td>")
                    .append(order.getValue().getProduct().getName())
                    .append("</td><td>")
                    .append(order.getValue().getQuantity())
                    .append("</td></tr>");
        }
        return sb.toString();
    }
}