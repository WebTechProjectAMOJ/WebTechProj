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
<style>

</style>
<body>
<div class="page">
    <jsp:include page="../components/user_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/search_header.jsp"/>
        <div class="order-details">
            <jsp:useBean id="orderDetails" scope="request" type="   models.order.Order"/>
            <h3>Order # ${orderDetails.id}</h3><hr>
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
            <h3>Messages</h3>
            <div class="message-window" style="overflow-y: scroll;height: 200px;border: 2px solid black;">

            </div>
            <div class="message-entry">
                    <input name="msg" style="width: 85%;padding: 0.5em;" placeholder="Enter message"/>
                    <button type="submit" style="    padding: 0.5em; background: darkseagreen;border-radius: 1em;width: 10em;" onclick="sendMsg()">Send!</button>
                    <input class="disappear" value="${requestScope.orderId}" name="orderId">
                    <input class="disappear" value="Customer" name="sender"/>
            </div>
        </div>
    </div>
</div>
<script>
    $("h2").text("Choose Delivery Driver");
</script>


<script>
    let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';
    function doPoll(){
        console.log("polling")
        $.get(link + "get-all-messages?orderid=" + '${requestScope.orderId}', function(data) {
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

