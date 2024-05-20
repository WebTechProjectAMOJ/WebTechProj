<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>

    <link rel="stylesheet" href="../../css/login-page-style.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<jsp:include page="../includes.jsp"/>

<body>
<div class="page">
    <jsp:include page="../components/user_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/search_header.jsp" />
        <jsp:include page="../components/section_row_scroll.jsp" />
    </div>

    <!-- Show all restaurant data -->
    <div class="all-restaurants-section">
        <h2>All Restaurants</h2>
        <ul>
            <c:forEach var="restaurant" items="${allRestaurants}">
                <li>
                    <h3>${restaurant.name}</h3>
                    <p>${restaurant.description}</p>
                    <p>Location: ${restaurant.location}</p>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

</body>
</html>
