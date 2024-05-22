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

    <link rel="stylesheet" href="../../css/login-page-style.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<body>
<div class="page">
    <jsp:include page="../components/user_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/search_header.jsp" />
        <jsp:include page="../components/popup_menu.jsp"/>
    </div>
</div>
</body>
</html>

<div class="add_popup" id="add-basket">
    <form class="form_add" method="post">
        <h3 style="align-self: center">Element Name</h3>
        <label>Cost: ...</label>
        <label> Description: ...</label>
        <label> Customisations:
            <input type="text" name="custom" required/>
        </label>
        <label> Quantity:
            <input type="number" name="quantity" required/>
        </label>

        <div class="footer">
            <label>Total Cost</label>
            <button type="submit" class="confirm_button" style="background-color: #B5C964;"> Add to Basket</button>
            <button  type="button" class="confirm_button" style="background-color: lightgrey; border-color: black;" onclick="closeBox()"> Cancel</button>
        </div>
    </form>
</div>

<script>
    function openBox() {
        document.getElementById("add-basket").style.display = "block";
    }

    function closeBox() {
        document.getElementById("add-basket").style.display = "none";
    }
</script>