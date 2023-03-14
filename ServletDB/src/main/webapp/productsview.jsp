<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products Table</title>
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
        <th>Product name</th>
        <th>Product price</th>
        <th>Product quantity</th>
    </tr>
    </thead>
    <tbody>
    <%=request.getAttribute("products")%>
    </tbody>
</table>
</body>
</html>
