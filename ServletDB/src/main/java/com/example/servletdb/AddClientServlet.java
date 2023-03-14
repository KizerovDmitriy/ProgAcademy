package com.example.servletdb;

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
@WebServlet(name = "addclient",value = "/addclient")
public class AddClientServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = ConnectionFactory.getConnection()){
            OrdersDAOImpl dao = new OrdersDAOImpl(connection);
            dao.addClient(req.getParameter("fname"),req.getParameter("lname"),req.getParameter("phone"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("index.html");
    }
}