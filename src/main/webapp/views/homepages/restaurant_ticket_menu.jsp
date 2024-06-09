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
    <title>Restaurant Menus</title>
    <jsp:useBean id="currentRestaurant" scope="application" class="models.user.Restaurant"/>
</head>
<jsp:include page="../includes.jsp"/>
<style>
    textarea {
        width: 90%;
        border-radius: 1em;
        padding: 1em;
    }

    input[type="text"] {
        width: 88%;
        padding: 1em;
    }

    #new-ticket-content h3 {
        padding: 1em;
    }

    button {
        margin: auto;
        width: 100%;
        height: 3em;
        background: beige;
        border: none;
        font-size: large;
    }
</style>

<body>
<div class="page">
    <jsp:include page="../components/restaurant_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/header.jsp" />
        <div class="ticket-tab-menu">
            <p id="raise-request" class="ticket-tab-active">Raise a Request</p>
            <p id="old-request">Old Requests</p>
        </div>
        <div class="ticket-text-content">
            <div class="new-ticket" id="new-ticket-content">
                <form action="${pageContext.request.contextPath}/restaurant-tickets" method="POST">
                    <h3>Create a New Ticket</h3>
                    <div>
                    <label> Subject: <br>
                    <input type="text" placeholder="Enter Ticket Title" name="subject" required>
                    </label>
                    </div>
                    <div>
                    <label>
                        Description:<br>
                        <textarea placeholder="Enter Description" rows="10" name="description" required></textarea>
                    </label>
                    </div>
                    <button type="submit">Submit Ticket</button>
                </form>
            </div>
            <div class="old-tickets disappear" id="old-ticket-content">
                Old
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
</script>

</html>
