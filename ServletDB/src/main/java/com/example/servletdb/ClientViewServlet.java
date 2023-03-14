package com.example.servletdb;

import classes.Client;
import classes.ConnectionFactory;
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

@WebServlet(name = "viewclients", value = "viewclients")
public class ClientViewServlet extends HttpServlet {
    private Map<Integer, Client> clients;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            OrdersDAOImpl dao = new OrdersDAOImpl(connection);
            clients = dao.viewAllClients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("clients", viewClients());
        req.getRequestDispatcher("/clientstable.jsp").forward(req, resp);
    }

    private String viewClients() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Client> client : clients.entrySet()) {
            sb.append("<tr><td>")
                    .append(client.getValue().getFirst_name())
                    .append("</td><td>")
                    .append(client.getValue().getLast_name())
                    .append("</td><td>")
                    .append(client.getValue().getPhone_number())
                    .append("</td></tr>");
        }
        return sb.toString();
    }
}