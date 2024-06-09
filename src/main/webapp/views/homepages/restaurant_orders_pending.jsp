<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="application" class="models.user.User"/>
<html>
<head>
    <title>Order Pending</title>
    <jsp:include page="../includes.jsp"/>
</head>

<jsp:useBean id="orders_to_scroll" scope="request" type="java.util.HashMap"/>

<body>
<div class="page">
    <jsp:include page="../components/restaurant_navbar.jsp"/>
    <div class="window">
        <c:if test="${sessionScope.message != null}">
            <script>
                alert("${sessionScope.message}")
            </script>
            <c:remove var="message"/>
        </c:if>
        <jsp:include page="../components/header.jsp"/>
        <c:set var="items_to_scroll" value="${orders_to_scroll}" scope="request"/>
        <jsp:include page="../components/section_grid_scroll.jsp"/>
    </div>
</div>
</body>
<script>
    $("h2").text("Pending Orders");
</script>
</html>

