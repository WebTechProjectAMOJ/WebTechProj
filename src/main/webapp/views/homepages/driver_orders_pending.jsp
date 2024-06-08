<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <jsp:include page="../includes.jsp"/>
    <script>

        let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';
        navigator.geolocation.getCurrentPosition((position) => {
            $.post("${link}set-driver-current-location", {
                "latitude" : position.coords.latitude,
                "longitude" : position.coords.longitude
            }).done(function (){
                alert("You will now recieve orders!")
            })
        });
    </script>
</head>
<jsp:useBean id="orders_to_scroll" scope="request" type="java.util.HashMap"/>

<body>
<div class="page">
    <jsp:include page="../components/driver_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/header.jsp"/>
        <c:set var="items_to_scroll" value="${orders_to_scroll}" scope="request"/>
        <jsp:include page="../components/section_grid_scroll.jsp"/>
    </div>
</div>
</body>
</html>
