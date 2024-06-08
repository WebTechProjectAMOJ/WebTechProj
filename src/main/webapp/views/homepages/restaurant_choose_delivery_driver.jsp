<%--
  Created by IntelliJ IDEA.
  User: margotrichez
  Date: 06/05/2024
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<html>
<head>
    <title>Order Open</title>
    <jsp:include page="../includes.jsp"/>
</head>
<jsp:useBean id="drivers" scope="request" type="java.util.ArrayList"/>

<body>
<div class="page">
    <jsp:include page="../components/restaurant_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/header.jsp" />
        <div class="order-details">
            <jsp:useBean id="orderDetails" scope="request" type="   models.order.Order"/>
            <h3>Order # ${orderDetails.id}</h3><hr>
            <jsp:useBean id="currentAddress" type="org.bson.json.JsonObject" scope="request"/>
            <input class="disappear" value='${currentAddress}' id="restaurant-address">
            <div>
                <c:forEach var="item" items="${orderDetails.order_items}">
                    <c:set var="fooditem" value="${item}" scope="page"/>
                    <jsp:useBean id="fooditem" scope="page" type="models.order.OrderItems"/>
                    <p>${fooditem.fooditem.name} x ${fooditem.quantity}</p>
                </c:forEach>
                <hr>
                <p>Order Total: €${orderDetails.total}</p>
            </div>
        </div>
        <div class="order-accept-boundary">
            <h1>Drivers:</h1>
            <div class="driver-card-region">
        <c:forEach var="driver" items="${drivers}">
            <jsp:useBean id="driver" type="models.user.Driver" scope="page"/>
            <div>
            <div class="driver-card">
            <p>${driver.firstName} ${driver.name}</p>
                <p class="driver-distance-text"></p>
                <input class="disappear" value='${driver.jsonCurrentPos}' id="driver-current-address">
            </div>
                <div class="add_popup" id="add-basket">
                    <form class="form_add" method="post" action="${pageContext.request.contextPath}/place-order-with-driver">
                        <h3 style="align-self: center" id="element-name">Driver Details</h3><hr>
                        <p><strong>First Name:</strong> ${driver.firstName}</p>
                        <p><strong>Name:</strong> ${driver.name}</p>
                        <p class="tool-text-parent"><strong>Tools:</strong> <span class="driver-tools-text"></span></p>
                        <input class="disappear" value="${orderDetails.id}" name="order_id"/>
                        <input class="disappear" value="${driver.id}" name="driver_id"/>
                        <div class="footer">
                            <label>€${orderDetails.total}</label>
                            <button type="submit" class="confirm_button" style="background-color: #B5C964;">Place Order</button>
                            <button  type="button" class="confirm_button" style="background-color: lightgrey; border-color: black;" onclick="closeBox(this)"> Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </c:forEach>
            </div>
        </div>
    </div>
</div>
<script>
    $("h2").text("Choose Delivery Driver");
</script>


<script>

    function updateDistanceBox(){
        $(".driver-distance-text").each(function (){
            let element = $(this).siblings("#driver-current-address");
            let data = JSON.parse(element.val());
            if(!data.set){
                $(this).text("Unavailable at the moment")
            }
            else {
                lat1 = data.latitude;
                long1 = data.longitude;
                lat2 = currentRestaurantAddress.geometry.location.lat;
                long2 = currentRestaurantAddress.geometry.location.lng;
                $(this).text(getDistanceFromLatLonInKm(lat1, long1, lat2, long2).toFixed(2).toString() + " Kms")
            }
        })
    }

    let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';

    function updateToolsTextsForDrivers(){
        console.log("called");
        $(".driver-tools-text").each(function (){
            let element = $(this);
            let data = element.parents("p.tool-text-parent").siblings("input[name='driver_id']").val();
            $.post(link + 'get-driver-tool-names', {
                driver_id : data,
            }).done(function (data){
                element.text(data.trim().slice(0,-1))
            })
        })
    }

    let currentRestaurantAddress = JSON.parse($("#restaurant-address").val());
    updateDistanceBox();
    updateToolsTextsForDrivers();

    function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
        var R = 6371; // Radius of the earth in km
        var dLat = deg2rad(lat2-lat1);  // deg2rad below
        var dLon = deg2rad(lon2-lon1);
        var a =
            Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
            Math.sin(dLon/2) * Math.sin(dLon/2)
        ;
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        var d = R * c; // Distance in km
        return d;
    }

    function deg2rad(deg) {
        return deg * (Math.PI/180)
    }

    $("div[class='driver-card']").on("click", function (){
        openBox($(this).siblings('#add-basket').get(0))
    })

    function openBox(element) {
        element.style.display = "block";
    }

    function closeBox(element) {
        element = $(element).closest('#add-basket').get(0)
        element.style.display = "none";
    }
</script>
</body>
</html>

