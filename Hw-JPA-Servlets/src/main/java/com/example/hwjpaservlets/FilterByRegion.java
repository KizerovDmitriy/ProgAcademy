package com.example.hwjpaservlets;

import dao.ApartmentsDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Apartments;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "region", value = "/region")
public class FilterByRegion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("region", byRegion(req.getParameter("byregion")));
        req.getRequestDispatcher("region.jsp").forward(req, resp);
    }

    private String byRegion(String filter) {
        StringBuilder sb = new StringBuilder();
        try (ApartmentsDao dao = new ApartmentsDao()) {
            List<Apartments> list = dao.getApartmentsByFilter(filter);
            for (Apartments apartments : list) {
                sb.append("<tr><td>")
                        .append(apartments.getRegion())
                        .append("</td><td>")
                        .append(apartments.getAddress())
                        .append("</td><td>")
                        .append(apartments.getArea())
                        .append("</td><td>")
                        .append(apartments.getRooms())
                        .append("</td><td>")
                        .append(apartments.getPrice())
                        .append("</td></tr>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
