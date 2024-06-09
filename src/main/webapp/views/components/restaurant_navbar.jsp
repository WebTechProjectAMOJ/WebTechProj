<%@ page contentType="text/html;charset=UTF-8" %>

<div class="navbar">
    <a href=""><h3>Orders</h3></a>
    <a href="${pageContext.request.contextPath}/restaurant-orders-to-accept">Pending Acceptance</a>
    <%--TODO--%>
    <a href="${pageContext.request.contextPath}/open-orders">Open</a>
    <a href="${pageContext.request.contextPath}/restaurant-orders-history">History</a>

    <a href=""><h3>Manage</h3></a>
    <a href="${pageContext.request.contextPath}/restaurant-menu-management">Menu</a>
    <a href="${pageContext.request.contextPath}/restaurant-offer-management">Offers</a>
    <a href="${pageContext.request.contextPath}/create-food-item">Add Food Item</a>
    <a href="${pageContext.request.contextPath}/restaurant-reviews"><h3>Reviews</h3></a>
    <a href="${pageContext.request.contextPath}/restaurant-tickets"><h3>Tickets</h3></a>

    <div class="user_nav">
        <div class="account">
            <svg xmlns="http://www.w3.org/2000/svg" height="2em" viewBox="0 0 448 512">
                <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                <style>svg {
                    fill: #000000
                }</style>
                <path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"></path>
            </svg>
            <a href="${pageContext.request.contextPath}/account-settings">
                <h3>Account<br>Settings</h3>
            </a>
        </div>
        <a href="./logout">logout</a>
    </div>
</div>
