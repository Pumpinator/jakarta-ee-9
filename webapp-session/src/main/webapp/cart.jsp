<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.java.webapp.servlet.model.*" %>
<%
    Cart cart = (Cart) session.getAttribute("cart");
%>
<html>
<head>
    <title>Shopping Cart | Session</title>
</head>
<body>
<div>
    <h1>Shopping Cart | Session</h1>
    <%if (cart == null || cart.getItems().isEmpty()) {%>
    <p>Cart is empty</p>
    <%} else {%>
    <div>

        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>PRICE</th>
                <th>QUANTITY</th>
                <th>TOTAL</th>
            </tr>
            </thead>
            <tbody>
            <%for (Item item : cart.getItems()) {%>
            <tr>
                <td><%=item.getProduct().getId()%>
                </td>
                <td><%=item.getProduct().getName()%>
                </td>
                <td><%=item.getProduct().getPrice()%>
                </td>
                <td><%=item.getQuantity()%>
                </td>
                <td><%=item.getTotal()%>
                </td>
            </tr>
            <%}%>
            <tr>
                <td colspan="4" style="text-align: right">Total</td>
                <td><%=cart.getTotal()%>
                </td>
            </tr>
            </tbody>
        </table>
        <%}%>
    </div>
    <div>
        <a href="products" style="text-align: end">View products...</a>
    </div>
    <div>
        <a href="/webapp-session">Go back.</a>
    </div>
</div>
</body>
</html>
