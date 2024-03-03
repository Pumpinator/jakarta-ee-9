<%@ page import="java.util.List" %>
<%@ page import="com.java.webapp.servlet.model.Category" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.java.webapp.servlet.model.Product" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: alejandro
  Date: 2/10/2024
  Time: 10:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
    Product product = (Product) request.getAttribute("product");
    String registerDate = product.getRegisterDate() != null ? product.getRegisterDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
%>
<html>
<head>
    <title>Product Form | Shopping Cart</title>
</head>
<body>
<div>
    <h1>Product Form</h1>
    <div>
        <form action="<%=request.getContextPath()%>/product" method="POST">
            <input type="hidden" name="id" value="<%=product.getId()%>">
            <div>
                <label>
                    Name
                    <input type="text" name="name" value="<%=product.getName() != null ? product.getName() : ""%>">
                </label>
            </div>
            <%if (errors != null && errors.containsKey("name")) {%>
            <div style="color: RED">
                <%=errors.get("name")%>
            </div>
            <%}%>
            <div>
                <label>
                    Price
                    <input type="number" name="price" value="<%=product.getPrice() > 0 ? product.getPrice() : ""%>">
                </label>
            </div>
            <%if (errors != null && errors.containsKey("price")) {%>
            <div style="color: RED">
                <%=errors.get("price")%>
            </div>
            <%}%>
            <div>
                <label>
                    Sku
                    <input type="text" name="sku" value="<%=product.getSku() != null ? product.getSku() : ""%>">
                </label>
            </div>
            <%if (errors != null && errors.containsKey("sku")) {%>
            <div style="color: RED">
                <%=errors.get("sku")%>
            </div>
            <%}%>
            <div>
                <label>
                    Register date
                    <input type="date" name="register_date" value="<%=registerDate%>">
                </label>
            </div>
            <%if (errors != null && errors.containsKey("register_date")) {%>
            <div style="color: RED">
                <%=errors.get("register_date")%>
            </div>
            <%}%>
            <div>
                <label>
                    Category
                    <select name="category">
                        <option>Select...</option>
                        <%for (Category category : categories) {%>
                        <option value="<%=category.getId()%>" <%=category.getId().equals(product.getCategory().getId()) ? "selected" : ""%>>
                            <%=category.getName()%>
                        </option>
                        <%}%>
                    </select>
                </label>
            </div>
            <%if (errors != null && errors.containsKey("category")) {%>
            <div style="color: RED">
                <%=errors.get("category")%>
            </div>
            <%}%>
            <div>
                <button type="submit"><%=product.getId() != null && product.getId() > 0 ? "Edit" : "Create"%></button>
            </div>
        </form>
        <div>
            <a href="<%=request.getContextPath()%>/products">Go back...</a>
        </div>
    </div>
</div>
</body>
</html>
