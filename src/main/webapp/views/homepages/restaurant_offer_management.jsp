<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Offer Management</title>

    <link rel="stylesheet" href="../../css/login-page-style.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<jsp:include page="../includes.jsp"/>
<jsp:useBean id="offers_to_scroll" scope="request" type="java.util.HashMap"/>

<body>
<div class="page">
    <jsp:include page="../components/restaurant_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/header.jsp" />
        <c:set var="items_to_scroll" value="${offers_to_scroll}" scope="request"/>
        <jsp:include page="../components/section_grid_scroll.jsp" />
        <div class="footer">
            <button type="button" class="confirm_button" style="background-color: #B5C964" onclick="openOffer()"> Add Offer</button>
        </div>

        <div class="add_popup" id="add-offer">
            <form class="form_add" method="post">
                <h3 style="align-self: center">Add Offer</h3>
                <label>Offer Name:
                    <input type="text" name="offer_name" required/>
                </label>

                <label> New Cost:
                    <input type="number" name="offer_price" required/>
                </label>

                <label> Menu Items:
                    <select id="myItems" >
                        <option value="items"> item1 </option>
                    </select>
                    <span>
                        <label> Quantity:
                            <input style="width:10vh " type="number" name="item_qty" required/>
                        </label>
                        <button type="button" class="add_button" >+</button>
                    </span>
                </label>

                <div class="footer">
                    <button type="submit" class="confirm_button" style="background-color: #B5C964;"> Add Offer</button>
                    <button  type="button" class="confirm_button" style="background-color: indianred; border-color: indianred;" onclick="closePopup()"> Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

<script>
    function openOffer() {
        document.getElementById("add-offer").style.display = "block";
    }

    function closePopup() {
        document.getElementById("add-offer").style.display = "none";

    }

</script>

