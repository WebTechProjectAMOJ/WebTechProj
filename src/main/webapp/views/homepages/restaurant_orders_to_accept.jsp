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
        <c:forEach var="order" items="${items_to_scroll}">
            Big buns
            <jsp:useBean id="order" class="models.order.Order" scope="page"/>
            <p>${order.total}</p>
        </c:forEach>
    </div>
</div>
</body>
</html>

