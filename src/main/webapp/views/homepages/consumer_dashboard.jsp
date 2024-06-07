<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>

<jsp:include page="../includes.jsp"/>
<jsp:useBean id="orders_to_scroll" scope="request" type="java.util.HashMap"/>
<jsp:useBean id="tags_to_scroll" scope="request" type="java.util.HashMap"/>

<body>
<div class="page">
    <jsp:include page="../components/user_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/search_header.jsp"/>
        <div class="grid background-colour-gray">
            <c:set var="items_to_scroll" value="${orders_to_scroll}" scope="request"/>
            <jsp:include page="../components/section_row_scroll.jsp"/>
        </div>
        <div class="grid">
            <c:set var="items_to_scroll" value="${tags_to_scroll}" scope="request"/>
            <jsp:include page="../components/section_row_scroll.jsp"/>
        </div>
    </div>
</div>

</body>
<script>
    let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';
    $(".box_container").on("click", function () {
        let hash = $(this).children('.id-data').text();
        console.log(hash);
        window.location.href = link + "restaurant?id=" + hash
    })
</script>
</html>
