<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>

<jsp:include page="../includes.jsp"/>

<body>
<div class="page">
    <jsp:include page="../components/user_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/search_header.jsp" />
        <div class="grid">
<%--            <jsp:include page="../components/section_row_scroll.jsp" /> <!-- display history -->--%>
            <jsp:include page="../components/section_row_scroll.jsp" /> <!-- display offers -->
        </div>
    </div>
</div>

</body>
<script>
    let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';
$(".box_container").on("click", function (){
    let hash = $(this).children('.id-data').text();
    console.log(hash);
    window.location.href = link + "restaurant?id="+hash
})
</script>
</html>
