<%@ page import="com.example.customerstable.CustomerView" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.customerstable.Customer" %>
<html>
<head>
    <title>Customers</title>
    <style>
        table {
            margin-left: auto;
            margin-right: auto;
        }

        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>FirstNAme</th>
        <th>LastName</th>
        <th>Age</th>
        <th>ID</th>
        <th>CashValue</th>
    </tr>
    </thead>
    <tbody>
     <%=request.getAttribute("customers")%>

<%--  <%
      List<Customer> customers = (List<Customer>) request.getAttribute("customersList");
      for (Customer s: customers){
          response.getWriter().print("<tr><td>" + s.getFirstName() + "</td><td>" + s.getLastName() + "</td></tr>");
      }
  %> --%>
  </tbody>
</table>
</body>
</html>