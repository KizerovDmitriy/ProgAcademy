
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders Table</title>
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
        <th>Client</th>
        <th>Product</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <%=request.getAttribute("orders")%>
    </tbody>
</table>
</body>
</html>
