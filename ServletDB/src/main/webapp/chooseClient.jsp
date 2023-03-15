<%--
  Created by IntelliJ IDEA.
  User: Dmitriy
  Date: 3/15/2023
  Time: 9:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients base</title>
</head>
<body>
<table>
    <tbody>
    <tr>
        <td>
    <form action="/addorder">
        <p>Please select Client data:</p>

        <%=request.getAttribute("chooseclients")%>

        <br>
        <p>Please select Product data:</p>

        <%=request.getAttribute("chooseProduct")%>
        <br>
        <label for="quantityorder">Input Product quantity:</label><br>
        <input type="text" id="quantityorder" name="quantityorder"><br><br>

        <input type="submit" value="Submit">
    </form>

        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
