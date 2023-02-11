package com.example.customerstable;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CustomerView extends HelloServlet {
    private final List<Customer> customers = Arrays.asList(new Customer("Alex", "Matveev", 18, "1", "132"),
            new Customer("Dmitriy", "Forsh", 32, "2", "55"),
            new Customer("Tamila", "Kizerova", 30, "3", "150"),
            new Customer("Dmitriy", "Kizerov", 32, "4", "2000"),
            new Customer("Margo", "Kizerova", 3, "5", "20"));

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("customersList", customers);
        request.setAttribute("customers", getCustomersTable());
        try {
            request.getRequestDispatcher("/customers.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCustomersTable() {
        StringBuilder sb = new StringBuilder();
        for (Customer customer : customers) {
            sb.append("<tr><td>")
                    .append(customer.getFirstName())
                    .append("</td><td>")
                    .append(customer.getLastName())
                    .append("</td><td>")
                    .append(customer.getAge())
                    .append("</td><td>")
                    .append(customer.getId())
                    .append("</td><td>")
                    .append(customer.getCashValue())
                    .append("</td></tr>");
        }
        return sb.toString();
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}