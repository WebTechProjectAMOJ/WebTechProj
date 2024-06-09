<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Restaurant Reviews</title>

    <link rel="stylesheet" href="../../css/login-page-style.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<jsp:include page="../includes.jsp"/>
<jsp:useBean id="fi_reviews_to_scroll" scope="request" type="java.util.HashMap"/>
<jsp:useBean id="combos_reviews_to_scroll" scope="request" type="java.util.HashMap"/>
<jsp:useBean id="resto_reviews_to_scroll" scope="request" type="java.util.HashMap"/>


<body>
<div class="page">
    <jsp:include page="../components/restaurant_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/header.jsp"/>

        <a class="sub_section_title">Restaurant</a>
        <c:set var="items_to_scroll" value="${resto_reviews_to_scroll}" scope="request"/>
        <jsp:include page="../components/section_grid_scroll.jsp">
            <jsp:param name="type" value="rating"/>
        </jsp:include>

        <a class="sub_section_title">Combos</a>
        <c:set var="items_to_scroll" value="${combos_reviews_to_scroll}" scope="request"/>
        <jsp:include page="../components/section_grid_scroll.jsp">
            <jsp:param name="type" value="rating"/>
        </jsp:include>

        <a class="sub_section_title">Food Items</a>
        <c:set var="items_to_scroll" value="${fi_reviews_to_scroll}" scope="request"/>
        <jsp:include page="../components/section_grid_scroll.jsp">
            <jsp:param name="type" value="rating"/>
        </jsp:include>
    </div>
</div>
<script>
    $("h2").text("Restaurant Reviews");
</script>
</body>
</html>