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
            Welcome, <c:out value="${username.get()}"/>
        </div>
    </c:if>
    <div>
        <a href="<c:out value="${pageContext.request.contextPath}" />/product">Add...</a>
    </div>
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
            <c:forEach items="${products}" var="product">
                <tr>
                    <td>
                        <c:out value="${product.id}"/>
                    </td>
                    <td>
                        <c:out value="${product.name}"/>
                    </td>
                    <td>
                        <c:out value="${product.category.name}"/>
                    </td>
                    <c:if test="${username.present}">
                        <td><c:out value="${product.price}"/>
                        </td>
                        <td>
                            <a href="<c:out value="${pageContext.request.contextPath}" />/cart/add?id=<c:out value="${product.id}" />">Add
                                to cart</a>
                        </td>
                        <td>
                            <a href="<c:out value="${pageContext.request.contextPath}" />/product?id=<c:out value="${product.id}" />">Edit
                                product</a>
                        </td>
                        <td>
                            <a href="<c:out value="${pageContext.request.contextPath}" />/product/delete?id=<c:out value="${product.id}" />"
                               onclick="return confirm('Are you sure?')">Delete product</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <a href="<c:out value="${pageContext.request.contextPath}" />">Go back...</a>
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
