<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: alejandro
  Date: 2/9/2024
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Products | Shopping Cart</title>
</head>
<body>
<div>
    <h1>Products</h1>
    <c:if test="${username.present}">
        <div>
            Welcome, ${username.get()}
        </div>
    <div>
        <a href="${pageContext.request.contextPath}/product">Add...</a>
    </div>
    </c:if>
    <div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Category</th>
                <c:if test="${(username.present)}">
                    <th>Price</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>
                            ${product.id}
                    </td>
                    <td>
                            ${product.name}
                    </td>
                    <td>
                            ${product.category.name}
                    </td>
                    <c:if test="${username.present}">
                        <td>
                                ${product.price}
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cart/add?id=${product.id}">Add
                                to cart</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/product?id=${product.id}">Edit
                                product</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/product/delete?id=${product.id}"
                               onclick="return confirm('Are you sure?')">Delete product</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <a href="${pageContext.request.contextPath}">Go back...</a>
    <div>
        <p>
            ${applicationScope.appMessage}
        </p>
        <p>
            ${requestScope.reqMessage}
        </p>
    </div>
</div>
</body>
</html>
