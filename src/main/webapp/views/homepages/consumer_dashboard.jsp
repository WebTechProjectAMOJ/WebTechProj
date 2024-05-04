<%--
  Created by IntelliJ IDEA.
  User: margotrichez
  Date: 04/05/2024
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<div class="page">
    <jsp:include page="../../components/user_navbar.jsp" />
    <div class="window">
        <jsp:include page="../../components/search_header.jsp" />
        <jsp:include page="../../components/section_row_scroll.jsp" />
    </div>

</div>

</body>
</html>
