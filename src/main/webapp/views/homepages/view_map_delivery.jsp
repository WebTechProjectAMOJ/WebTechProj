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
                    src="https://www.google.com/maps/embed/v1/directions?key=xx
  &origin=${requestScope.curr_lat},%20${requestScope.curr_lng}
  &destination=${requestScope.home_lat},%20${requestScope.home_lng}
  &waypoints=${requestScope.restaurant_lat},%20${requestScope.restaurant_lng}
  &avoid=tolls&mode=bicycling"
                    allowfullscreen>
            </iframe>
            <input class="disappear" value="${requestScope.order_id}" id="order-id-data"/>
            <button id="complete-order-btn">Complete Order</button>
        </div>
        <div class="order-accept-boundary">
            <h3>Messages</h3>
            <div class="message-window" style="overflow-y: scroll;height: 200px;border: 2px solid black;">

            </div>
            <div class="message-entry">
                <input name="msg" style="width: 85%;padding: 0.5em;" placeholder="Enter message"/>
                <button type="submit" style="    padding: 0.5em; background: darkseagreen;border-radius: 1em;width: 10em;" onclick="sendMsg()">Send!</button>
                <input class="disappear" value="${requestScope.order_id}" name="orderId">
                <input class="disappear" value="Driver" name="sender"/>
            </div>
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
    let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';
    function doPoll(){
        $.get(link + "get-all-messages?orderid=" + "${requestScope.order_id}", function(data) {
            $(".message-window").html(data);
            setTimeout(doPoll,5000);
        });
    }
    doPoll()

    function sendMsg(){
        let sender = $("input[name='sender']").val()
        let orderId = $("input[name='orderId']").val()
        let msg = $("input[name='msg']").val()
        $.post(link+ 'get-all-messages', {
            "sender" : sender,
            "orderId" : orderId,
            "msg": msg
        }).done(data => {
            alert(data);
        })
    }

</script>
</body>
</html>
