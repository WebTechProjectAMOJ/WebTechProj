<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <jsp:include page="../includes.jsp"/>
</head>
<jsp:useBean id="orders_to_scroll" scope="request" type="java.util.HashMap"/>

<body>
<div class="page">
    <jsp:include page="../components/driver_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/header.jsp"/>
        <c:set var="items_to_scroll" value="${orders_to_scroll}" scope="request"/>
        <jsp:include page="../components/section_grid_scroll.jsp"/>
    </div>
</div>
</body>
</html>
