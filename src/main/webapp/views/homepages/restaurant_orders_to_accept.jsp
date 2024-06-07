<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="application" class="models.user.User"/>
<html>
<head>
    <title>Order Pending</title>
    <jsp:include page="../includes.jsp"/>
</head>

<body>
<div class="page">
    <jsp:include page="../components/restaurant_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/header.jsp"/>
        <c:set var="items_to_scroll" value="${order_list}" scope="request"/>
        <div class="order-accept-boundary">
        <c:forEach var="order" items="${items_to_scroll}">
            <jsp:useBean id="order" class="models.order.Order" scope="page"/>
            <div class="order-accept">
                <div class="order-accept-nameblock">
            <c:forEach var="item" items="${order.order_items}">
                <c:set var="fooditem" value="${item}" scope="page"/>
                <jsp:useBean id="fooditem" scope="page" type="models.order.OrderItems"/>
                <p>${fooditem.fooditem.name} x ${fooditem.quantity}</p>
            </c:forEach>
                </div>
                <div class="order-accept-priceblock">
                    €${fooditem.price}
                </div>
                <div class="order-accept-buttonbar">
                    <button class="accept-order" onclick="orderStatus('accept', '${order.id}')">Accept</button>
                    <button class="reject-order" onclick="orderStatus('reject', '${order.id}')">Reject</button>
                </div>
            </div>
        </c:forEach>
        </div>
    </div>
</div>
<script>
    function orderStatus(type, hash){
        let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';
        $.post(link+'change-order-status', {
            "type": type,
            "hash": hash
        }).done(function (data) {
            console.log(data)
            location.reload();
        })
    }
</script>
</body>
</html>

