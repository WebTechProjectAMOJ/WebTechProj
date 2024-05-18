<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="application" class="models.user.User"/>
<html>
<head>
    <title>Dashboard</title>

    <link rel="stylesheet" href="../../css/login-page-style.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<jsp:include page="../includes.jsp"/>

<body>
<div class="page">
    <jsp:include page="../components/restaurant_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/header.jsp"/>
        <jsp:include page="../components/section_grid_scroll.jsp"/>
    </div>
</div>
</body>
</html>

