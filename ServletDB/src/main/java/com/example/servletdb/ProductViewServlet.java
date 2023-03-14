package com.example.servletdb;

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
@WebServlet(name = "viewproduct",value = "viewproduct")
public class ProductViewServlet extends HttpServlet {
    private Map<Integer, Product> products;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = ConnectionFactory.getConnection()){
            OrdersDAOImpl dao = new OrdersDAOImpl(connection);
            products = dao.viewAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("products",viewProducts());
        req.getRequestDispatcher("/productsview.jsp").forward(req,resp);
    }
    private String viewProducts(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer,Product> product : products.entrySet()){
            sb.append("<tr><td>")
                    .append(product.getValue().getName())
                    .append("</td><td>")
                    .append(product.getValue().getPrice())
                    .append("</td><td>")
                    .append(product.getValue().getQuantity())
                    .append("</td></tr>");
        }
        return sb.toString();
    }
}
