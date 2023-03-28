<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 3/28/2023
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View All Apartments</title>
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
    <th>Apartments region</th>
    <th>Apartments address</th>
    <th>Apartments area</th>
    <th>Apartments rooms</th>
    <th>Apartments price</th>
  </tr>
  </thead>
  <tbody>
  <%=request.getAttribute("view")%>
  </tbody>
</table>
</body>
</html>
