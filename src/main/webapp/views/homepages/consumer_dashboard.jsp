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
        <div class="grid">
            <jsp:include page="../components/section_row_scroll.jsp" /> <!-- display history -->
            <jsp:include page="../components/section_row_scroll.jsp" /> <!-- display offers -->
        </div>
    </div>
</div>

</body>
</html>
