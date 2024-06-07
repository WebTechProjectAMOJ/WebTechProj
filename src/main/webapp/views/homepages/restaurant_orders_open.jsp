<%--
  Created by IntelliJ IDEA.
  User: margotrichez
  Date: 06/05/2024
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<html>
<head>
    <title>Order Open</title>
    <jsp:include page="../includes.jsp"/>
</head>
<jsp:useBean id="orderList" scope="request" type="java.util.ArrayList"/>

<body>
<div class="page">
    <jsp:include page="../components/restaurant_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/header.jsp" />
        <div class="order-accept-boundary">
        <c:forEach var="order" items="${orderList}">
            <div>
            <jsp:useBean id="order" scope="page" type="models.order.Order"/>
            <div class="order-accept order-accepted">
                <div class="order-accept-nameblock">
                    <c:forEach var="item" items="${order.order_items}">
                        <c:set var="fooditem" value="${item}" scope="page"/>
                        <jsp:useBean id="fooditem" scope="page" type="models.order.OrderItems"/>
                            <p>${fooditem.fooditem.name} x ${fooditem.quantity}</p>
                    </c:forEach>
                </div>
                <div class="order-accept-priceblock">
                    €${order.total}
                </div>
            </div>
                <div class="add_popup" id="add-basket">
                    <form class="form_add" method="post" action="${pageContext.request.contextPath}/choose-delivery-driver">
                        <h3 style="align-self: center" id="element-name">Order Details</h3><hr>
                        <c:forEach var="item2" items="${order.order_items}">
                            <c:set var="fooditem2" value="${item2}" scope="page"/>
                            <jsp:useBean id="fooditem2" scope="page" type="models.order.OrderItems"/>
                                <h4>${fooditem2.fooditem.name} x ${fooditem2.quantity}</h4>
                                <p>${fooditem2.customizations}</p>
                            <hr>
                        </c:forEach>
                        <input class="disappear" value="${order.id}">
                        <div class="footer">
                            <label>€${order.total}</label>
                            <button type="submit" class="confirm_button" style="background-color: #B5C964;">Ready For Delivery</button>
                            <button  type="button" class="confirm_button" style="background-color: lightgrey; border-color: black;" onclick="closeBox(this)"> Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</div>
<script>
    $("h2").text("Open Orders");
</script>


<script>

    $("div[class='order-accept order-accepted']").on("click", function (){
        openBox($(this).siblings('#add-basket').get(0))
    })

    function openBox(element) {
        element.style.display = "block";
    }

    function closeBox(element) {
        element = $(element).closest('#add-basket').get(0)
        element.style.display = "none";
    }
</script>
</body>
</html>

