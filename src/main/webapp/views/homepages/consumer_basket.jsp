<%--
  Created by IntelliJ IDEA.
  User: margotrichez
  Date: 06/05/2024
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Order basket</title>

</head>
<jsp:include page="../includes.jsp"/>
<body>
<div class="page">
    <jsp:include page="../components/user_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/search_header.jsp" />
        <jsp:useBean id="items_to_scroll" scope="request" type="java.util.HashMap"/>
        <c:forEach var="key" items="${items_to_scroll.keySet()}">
            <c:set var="restaurant" value="${key}" scope="page"/>
            <jsp:useBean id="restaurant" scope="page" type="models.user.Restaurant"/>
            <a class="section_title">${restaurant.name}</a>
            <div class="grid_container">
                <c:set var="itemslist" value="${items_to_scroll.get(key)}" scope="page"/>
                <jsp:useBean id="itemslist" scope="page" class="models.order.Order"/>
                <c:forEach var="item" items="${itemslist.order_items}">
                    <c:set var="fooditem" value="${item}" scope="page"/>
                    <jsp:useBean id="fooditem" scope="page" type="models.order.OrderItems"/>

                    <div class="box_container">
                        <div class="box"><img src="${fooditem.fooditem.photo_url}"/> </div>
                        <h3>${fooditem.quantity} x ${fooditem.fooditem.name}</h3>
                        <p style="display: none" class="id-data">${fooditem.fooditem.id}</p>
                        <a>€${fooditem.price}</a>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${fn:contains(requestScope['jakarta.servlet.forward.request_uri'], 'view-basket')}">
                <div class="price_bar">
                    <p>Total: €${itemslist.total}</p>
                </div>
                <div class="footer">
                    <form action="./make_payment" method="POST">
                        <div class="disappear">
                            <input id="address-key" name="address-key"/>
                            <input name="restaurant-key" value="${restaurant.id}"/>
                        </div>
                        <button class="confirm_button">Pay!</button>
                    </form>
                </div>
            </c:if>
        </c:forEach>
    </div>
</div>
</body>
</html>
<script>
    function orderSummary(key) {
        window.location.href = "./order_summary.jsp?key=" + key;
    }

    $('.confirm_button').on('click',function() {
        setAddressKey();
    });

    function setAddressKey(){
        console.log("address key", $("#address-select").val())
        $("#address-key").val($("#address-select").val())
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
