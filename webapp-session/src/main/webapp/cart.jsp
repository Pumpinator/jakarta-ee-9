<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Shopping Cart | Session</title>
</head>
<body>
<div>
    <h1>Shopping Cart | Session</h1>
    <c:choose>
        <c:when test="${cart.items.isEmpty()}">
            <p>Cart is empty</p>
        </c:when>
        <c:otherwise>

            <div>
                <form name="cartform" action="${pageContext.request.contextPath}/cart/update" method="POST">

                    <table>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>NAME</th>
                            <th>PRICE</th>
                            <th>QUANTITY</th>
                            <th>TOTAL</th>
                            <th>DELETE</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${cart.items}">
                            <tr>
                                <td>${item.product.id}
                                </td>
                                <td>${item.product.name}
                                </td>
                                <td>${item.product.price}
                                </td>
                                <td><input type="text" size="4" name="quantity_${item.product.id}"
                                           value="${item.quantity}"/></td>
                                <td>${item.total}
                                </td>
                                <td><input type="checkbox" value="${item.product.id}" name="updateItems"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="4" style="text-align: right">Total</td>
                            <td>${cart.total}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
    <div>
        <a href="javascript:document.cartform.submit();" style="text-align: end">Update</a>
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
