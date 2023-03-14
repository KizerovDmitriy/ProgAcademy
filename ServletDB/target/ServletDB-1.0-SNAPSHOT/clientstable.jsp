<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients</title>
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
        <th>FirstName</th>
        <th>LastName</th>
        <th>PhoneNumber</th>
    </tr>
    </thead>
    <tbody>
    <%=request.getAttribute("clients")%>
    </tbody>
</table>
</body>
</html>