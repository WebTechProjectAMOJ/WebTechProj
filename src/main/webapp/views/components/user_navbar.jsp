<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar">
    <a href="${pageContext.request.contextPath}/customer-landing"><h3>Dashboard</h3></a>
    <a href="${pageContext.request.contextPath}/view-basket">Basket</a>
    <a href="${pageContext.request.contextPath}/customer-orders-ongoing">Ongoing</a>
    <a href="${pageContext.request.contextPath}/customer-history">History</a>

    <div class="user_nav">
        <div class="account">
            <svg xmlns="http://www.w3.org/2000/svg" height="2em" viewBox="0 0 448 512">
                <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                <style>svg {
                    fill: #000000
                }</style>
                <path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"/>
            </svg>
            <a href="${pageContext.request.contextPath}/account-settings">
                <h3>Account<br>Settings</h3>
            </a>
        </div>
        <a href="${pageContext.request.contextPath}/logout">logout</a>
    </div>
</div>
