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
    <form class="form_add" method="post" action="${pageContext.request.contextPath}/add-to-basket">
        <h3 style="align-self: center" id="element-name">Element Name</h3>
        <input style="display: none" id="element-name-data" name="name"/>
        <label>Cost: <span id="element-cost"></span></label>
            <label> Description: <span id="element-desc"></span></label>
        <input style="display: none" id="element-cost-data" name="cost"/>
        <label> Customisations:
            <input type="text" name="custom" value="N/A" required/>
        </label>
        <label> Quantity:
            <input type="number" name="quantity" step="1" value="1" required/>
        </label>
        <input style="display: none" value="${param.id}" name="forwardto"/>
        <input style="display: none" value="" name="idHash" id = "idHash"/>
        <input style="display: none" value="" name="photo_url" id="photo-url"/>
        <input style="display: none" value="${currentRestaurant.getId()}" name="restaurantId"/>
        <div class="footer">
            <label>Total Cost</label>
            <button type="submit" class="confirm_button" style="background-color: #B5C964;"> Add to Basket</button>
            <button  type="button" class="confirm_button" style="background-color: lightgrey; border-color: black;" onclick="closeBox()"> Cancel</button>
        </div>
    </form>
</div>

<script>
    let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';

    $(".box_container").on("click", function (){
        let hash = $(this).children('.id-data').text();
        $.get("${link}get-food-item-details", {id:hash}, function (data){
            console.log(data)
            $("#element-name").text(data.name);
            $("#element-name-data").val(data.name);
            $("#element-cost").text("€ " + data.price);
            $("#element-cost-data").val(data.price);
            $("#element-desc").text(data.description.trim().slice(0, -1));
            $("#photo-url").val(data.photo_url)
            $("#idHash").val(hash);
        })
        openBox()
    })

    function openBox() {
        document.getElementById("add-basket").style.display = "block";
    }

    function closeBox() {
        document.getElementById("add-basket").style.display = "none";
    }
</script>