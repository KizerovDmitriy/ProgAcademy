package com.example.hwjpaservlets;

import dao.ApartmentsDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Apartments;


@WebServlet(name = "new", value = "/new")
public class AddApartments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        addNewApartments(req, resp);
    }

    private void addNewApartments(HttpServletRequest req, HttpServletResponse resp) {

        try (ApartmentsDao dao = new ApartmentsDao()) {
            Apartments apartments = new Apartments(req.getParameter("region"),
                    req.getParameter("address"),
                    Double.parseDouble(req.getParameter("area")),
                    Integer.parseInt(req.getParameter("rooms")),
                    Double.parseDouble(req.getParameter("price")));
            dao.addApartments(apartments);
            resp.sendRedirect("index.html");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
