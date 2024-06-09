<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: margotrichez
  Date: 06/05/2024
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket Management</title>
    <link rel="stylesheet" href="./css/login-page-style.css">
    <link rel="stylesheet" href="./css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<style>
    button {
        background: darkgreen;
        border: 3px black solid;
        border-radius: 1em;
        padding: 1em;
        margin-top: 1em;
        width: 100%;
    }

    .ticket-item {
        background: aliceblue;
        margin-bottom: 3em;
    }

    div#new-ticket-content {
        background: #a2a8d3;
        border: 3px solid black;
    }

    div#old-ticket-content {
        background: #a2a8d3;
        border: 3px solid black;
    }
</style>

<body>
<div class="page">
    <div class="window">
        <div class="ticket-tab-menu">
            <p id="raise-request" class="ticket-tab-active">New Tickets</p>
            <p id="old-request">Old Tickets</p>
        </div>
        <div class="ticket-text-content">
            <div class="new-ticket" id="new-ticket-content">
                    <jsp:useBean id="newtickets" scope="request" type="java.util.ArrayList"/>
                    <c:forEach var="newticket" items="${newtickets}">
                        <jsp:useBean id="newticket" type="models.tickets.Ticket"/>
                        <div class="ticket-item">
                            <h3 class="ticket-title">${newticket.title}</h3><hr>
                            <p class="ticket-status">Status: ${newticket.status}</p>
                            <p class="ticket-desc">${newticket.description}</p>
                            <button onclick="markDone('${newticket.id}')">Done</button>
                        </div>
                    </c:forEach>
            </div>
            <div class="old-tickets disappear" id="old-ticket-content">
                <jsp:useBean id="tickets" scope="request" type="java.util.ArrayList"/>
                <c:forEach var="ticket" items="${tickets}">
                    <jsp:useBean id="ticket" type="models.tickets.Ticket"/>
                    <div class="ticket-item">
                        <h3 class="ticket-title">${ticket.title}</h3><hr>
                        <p class="ticket-status">Status: ${ticket.status}</p>
                        <p class="ticket-desc">${ticket.description}</p>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';
</script>

<script>
    $("h2").text("Raise a Ticket");
</script>

<script>
$("#old-request").on("click", function (){
    $("#new-ticket-content").toggleClass("disappear", true);
    $("#old-ticket-content").toggleClass("disappear", false);
    $(this).toggleClass("ticket-tab-active", true);
    $("#raise-request").toggleClass("ticket-tab-active", false);
})

$("#raise-request").on("click", function (){
    $("#new-ticket-content").toggleClass("disappear", false);
    $("#old-ticket-content").toggleClass("disappear", true);
    $(this).toggleClass("ticket-tab-active", true);
    $("#old-request").toggleClass("ticket-tab-active", false);
})

function markDone(divid){
    let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';
    console.log("done")
    $.post(link + '/admin-tickets', {
        "id": divid
    }
    ).done((data) => {
        console.log(data);
        window.location.reload();
    })
}
</script>

</html>
