<%--
  Created by IntelliJ IDEA.
  User: margotrichez
  Date: 22/05/2024
  Time: 01:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>

    <link rel="stylesheet" href="../../css/login-page-style.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<body>
<div class="page">
    <jsp:include page="../components/user_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/search_header.jsp" />
        <jsp:include page="../components/section_grid_scroll.jsp" /> <!-- display restaurants -->
    </div>
</div>
</body>
</html>

<script>
    // need to change name of function cause clashes with the openMenu of the Restaurant Menu View
    //in items box component can we do an if condition to check the url and change the name ord onclick method ?
    function openMenu() {
        window.location.href = "/restaurant_menus.jsp";
    }
</script>



