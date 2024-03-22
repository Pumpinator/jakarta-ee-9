<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: alejandro
  Date: 2/10/2024
  Time: 10:40 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Product Form | Shopping Cart</title>
</head>
<body>
<div>
    <h1>Product Form</h1>
    <div>
        <form action="${pageContext.request.contextPath}/product" method="POST">
            <input type="hidden" name="id" value="${product.id}">
            <div>
                <label>
                    Name
                    <input type="text" name="name" value="${product.name}">
                </label>
            </div>
            <c:if test="${errors != null && not empty errors.name}" >
            <div style="color: RED">
                ${errors.get("name")}
            </div>
            </c:if>
            <div>
                <label>
                    Price
                    <input type="number" name="price" value="${product.price > 0 ? product.price : ""}">
                </label>
            </div>
            <c:if test="${errors != null && not empty errors.price}" >
            <div style="color: RED">
                ${errors.get("price")}
            </div>
            </c:if>
            <div>
                <label>
                    Sku
                    <input type="text" name="sku" value="${product.sku != null ? product.sku : ""}">
                </label>
            </div>
            <c:if test="${errors != null && not empty errors.sku}" >
            <div style="color: RED">
                ${errors.get("sku")}
            </div>
            </c:if>
            <div>
                <label>
                    Register date
                    <input type="date" name="register_date"
                           value="${product.registerDate != null ? product.registerDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""}">
                </label>
            </div>
            <c:if test="${errors != null && not empty errors.register_date}" >
            <div style="color: RED">
                ${errors.get("register_date")}
            </div>
            </c:if>
            <div>
                <label>
                    Category
                    <select name="category">
                        <option>Select...</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}" ${category.id.equals(product.category.id) ? 'selected' : ''}>
                            ${category.name}
                            </option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <c:if test="${errors != null && not empty errors.category}" >
            <div style="color: RED">
                ${errors.get("category")}
            </div>
            </c:if>
            <div>
                <button type="submit">${product.id != null && product.id > 0 ? "Edit" : "Create"}
                </button>
            </div>
        </form>
        <div>
            <a href="${pageContext.request.contextPath}/products">Go back...</a>
        </div>
    </div>
</div>
</body>
</html>
