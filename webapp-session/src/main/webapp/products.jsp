<%@ page import="java.util.*, com.java.webapp.servlet.model.*" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    Optional<String> username = (Optional<String>) request.getAttribute("username");

    String reqMessage = (String) request.getAttribute("request key"); // ONE EVERY REQUEST
    String appMessage = (String) getServletContext().getAttribute("context key"); // SINGLETON
%><%--
  Created by IntelliJ IDEA.
  User: alejandro
  Date: 2/9/2024
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products | Shopping Cart</title>
</head>
<body>
<div>
    <h1>Products</h1>
    <%if (username.isPresent()) {%>
    <div>Welcome, <%=username.get()%></div>
    <%}%>
    <div>
        <a href="<%=request.getContextPath()%>/product">Add...</a>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Category</th>
                <%if (username.isPresent()) {%>
                <th>Price</th>
                <th></th>
                <th></th>
                <th></th>
                <%}%>
            </tr>
            </thead>
            <tbody>
            <%for (Product product : products) {%>
            <tr>
                <td><%=product.getId()%>
                </td>
                <td><%=product.getName()%>
                </td>
                <td><%=product.getCategory().getName()%>
                </td>
                <%if (username.isPresent()) {%>
                <td><%=product.getPrice()%>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/cart/add?id=<%=product.getId()%>">Add to cart</a>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/product?id=<%=product.getId()%>">Edit product</a>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/product/delete?id=<%=product.getId()%>" onclick="return confirm('Are you sure?')">Delete product</a>
                </td>
                <%}%>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
    <a href="<%=request.getContextPath()%>">Go back...</a>
    <div>
        <p><%=appMessage%>
        </p>
        <p><%=reqMessage%>
        </p>
    </div>
</div>
</body>
</html>
