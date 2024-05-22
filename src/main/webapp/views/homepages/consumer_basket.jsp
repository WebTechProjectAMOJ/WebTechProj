<%--
  Created by IntelliJ IDEA.
  User: margotrichez
  Date: 06/05/2024
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order basket</title>

    <link rel="stylesheet" href="../../css/login-page-style.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<body>
<div class="page">
    <jsp:include page="../components/user_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/search_header.jsp" />
            <jsp:include page="../components/section_grid_scroll.jsp" />
        <div class="footer">
            <button class="confirm_button" onclick="orderSummary()">Go to checkout</button>
        </div>
    </div>
</div>
</body>
</html>
<script>
    function orderSummary() {
        window.location.href = "./order_summary.jsp";
    }
</script>


<div class="add_popup" id="edit-basket">
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
            <button type="submit" class="confirm_button" style="background-color: #B5C964;"> Save</button>
            <button  type="button" class="confirm_button" style="background-color: indianred; border-color: indianred;" onclick="deleteItem()">Delete</button>
            <button  type="button" class="confirm_button" style="background-color: lightgrey; border-color: lightgrey;" onclick="closeBox()">Cancel</button>
        </div>
    </form>
</div>

<script>
    function openBox() {
        document.getElementById("edit-basket").style.display = "block";
    }

    function closeBox() {
        document.getElementById("edit-basket").style.display = "none";
    }

    function deleteItem() {
        // code to delete item
    }
</script>
