package com.example.customerstable;

import java.io.*;

import jakarta.servlet.http.*;

public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello</h1>");
       // out.println("<h1>" + request.getParameter("param") + "</h1>");
        out.println("</body></html>");
    }
}