<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <jsp:include page="../includes.jsp"/>
</head>

<body>
<div class="page">
    <jsp:include page="../components/driver_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/header.jsp"/>
        <div class="order-accept-boundary">
            <h1>Order #: ${requestScope.order_id}</h1>
            <iframe
                    width="850"
                    height="450"
                    referrerpolicy="no-referrer"
                    frameborder="0" style="border:0"
                    src="https://www.google.com/maps/embed/v1/directions?key=AIzaSyCRvQUyNFUUfZ-_vc99VAN_EDz0KKMZaRQ
  &origin=${requestScope.curr_lat},%20${requestScope.curr_lng}
  &destination=${requestScope.home_lat},%20${requestScope.home_lng}
  &waypoints=${requestScope.restaurant_lat},%20${requestScope.restaurant_lng}
  &avoid=tolls&mode=bicycling"
                    allowfullscreen>
            </iframe>
            <input class="disappear" value="${requestScope.order_id}" id="order-id-data"/>
            <button id="complete-order-btn">Complete Order</button>
        </div>
    </div>
</div>

<script>
    $("h2").text("Orders to Deliver");
</script>
<script>
    $("#complete-order-btn").on("click", function(){
        let id = $("#order-id-data").val()
        let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';
        $.post(link + 'finalize-order',{
            order_id: id
        }).done(function(data){
            alert(data);
            window.location.href = link + 'driver-landing';
        })
    })
</script>
</body>
</html>
