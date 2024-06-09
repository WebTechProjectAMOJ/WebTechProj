<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 22/05/2024
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration</title>
    <head>
        <link rel="stylesheet" href="./css/login-page-style.css">
        <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    </head>
    <style>
        a {
            background: green;
            border: 2px darkgreen solid;
            border-radius: 1em;
            color: white;
            display: block;
            width: 100%;
            padding: 1em;
            margin: 1em;
            font-size: large;
            text-align: center;
            text-decoration: none;
            transition: scale 0.3s ease;
        }
        a:hover {
            scale: 1.03;
            text-decoration: underline;
        }
        p.adminMessage {
            background: aliceblue;
            border: cornflowerblue 3px solid;
            width: 100%;
            padding: 2em;
            border-radius: 30px;
        }
        a.logout-btn-admin {
            background: crimson;
            color: antiquewhite;
        }
    </style>
</head>
<body>
<div class="container">
    <div id="logo-login">
        <img id="login-logo-img" src="assets/food-dispatch-logo.png" alt="logo">
    </div>
    <h1>Admin Dashboard</h1>

    <c:if test="${sessionScope.adminMessage != null}">
        <p class="adminMessage">${sessionScope.adminMessage}</p>
        <c:remove var="adminMessage"/>
    </c:if>

    <a href="${pageContext.request.contextPath}/add-tag-admin">Add a Tag</a>
    <a href="${pageContext.request.contextPath}/add-tool-admin">Add a Tool</a>
    <a href="${pageContext.request.contextPath}/add-delivery-service-admin">Add A Delivery Service</a>
    <a href="${pageContext.request.contextPath}/admin-tickets">View Tickets</a>
    <a href="${pageContext.request.contextPath}/logout" class="logout-btn-admin">Logout</a>
</div>
</body>
</html>
