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
<jsp:useBean id="orders" scope="request" type="java.util.ArrayList"/>
<body>
<div class="page">
    <jsp:include page="../components/driver_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/header.jsp"/>
        <div class="order-accept-boundary">
            <h1>Your Orders:</h1>
        <c:forEach var="order" items="${orders}">
            <jsp:useBean id="order" scope="page" type="models.order.Order"/>
            <div class="driver-order-card" title="Go to Order">
                <p><strong>Order #${order.id}</strong></p>
                <p class="order-short-address"></p>
                <c:set scope="page" var="order_address" value="${order.delivery_address_json}"/>
                <jsp:useBean id="order_address" scope="page" type="org.bson.json.JsonObject"/>
                <input class="disappear" value='${order_address}' datafld="order-address"/>
                <input class="disappear" value='${order.id}' datafld="order-id"/>
            </div>
        </c:forEach>
        </div>
    </div>
</div>
<script>
    $(".order-short-address").each(function (){
        let element = $(this);
        let data = element.siblings("input[datafld='order-address']").val();
        data = JSON.parse(data);
        element.html("<strong>Address: </strong>" + data.name);
    })

    $(".order-short-address").each(function (){
        let element = $(this);
        let data = element.siblings("input[datafld='order-address']").val();
        data = JSON.parse(data);
        element.html("<strong>Address: </strong>" + data.name);
    })

    $(".driver-order-card").on("click", function (){
        let order_id;
        hasheddata = $(this).children("input[datafld='order-id']").val();
        order_id = hasheddata;
        let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';
        window.location.href = link + "deliver-order?id=" + order_id;
    })
</script>

<script>
    $("h2").text("Orders to Deliver");
</script>
</body>
</html>
