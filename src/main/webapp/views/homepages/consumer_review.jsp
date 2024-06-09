<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consumer Review Settings</title>

</head>
<jsp:include page="../includes.jsp"/>
<jsp:include page="../AccountCreations/includecsscreate.jsp"/>
<body>


<jsp:useBean id="food_items" scope="request" type="java.util.ArrayList"/>
<jsp:useBean id="order" scope="request" type="models.order.Order"/>
<jsp:useBean id="restaurant_id" scope="request" type="org.bson.types.ObjectId"/>
<jsp:useBean id="driver_id" scope="request" type="org.bson.types.ObjectId"/>

<div class="page">
    <jsp:include page="../components/user_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/header.jsp"/>
        <div class="popup_window"
             style="align-self: center; position: static; overflow-y: auto; padding: 1rem ; width: 100vh;">
            <a class="section_title" style="border:None;">Personal Details</a>
            <div class="divider"></div>
            <form action="${pageContext.request.contextPath}/customer-review?id=${order.getId()}" method="POST">
                <div class="grid_container" style="grid-template-columns: auto auto;">

                    <input name="action" type="hidden" value="submit-review">

                    <a> Restaurant Rating: </a>
                    <div class="setting_inputs">
                        <input type="hidden" name="restaurant_id" value="${restaurant_id}">
                        <select name="restaurant-rating-${restaurant_id}">
                            <option value="1">&#9733;</option>
                            <option value="2">&#9733;&#9733;</option>
                            <option value="3">&#9733;&#9733;&#9733;</option>
                            <option value="4">&#9733;&#9733;&#9733;&#9733;</option>
                            <option selected value="5">&#9733;&#9733;&#9733;&#9733;&#9733;</option>
                        </select>
                        <label>
                            <input name="restaurant-feedback-${restaurant_id}" type="text"
                                   placeholder="Add some feedback..." value="">
                        </label>
                    </div>

                    <a> Driver Rating: </a>
                    <div class="setting_inputs">
                        <input type="hidden" name="driver_id" value="${driver_id}">
                        <select name="driver-rating-${driver_id}">
                            <option value="1">&#9733;</option>
                            <option value="2">&#9733;&#9733;</option>
                            <option value="3">&#9733;&#9733;&#9733;</option>
                            <option value="4">&#9733;&#9733;&#9733;&#9733;</option>
                            <option selected value="5">&#9733;&#9733;&#9733;&#9733;&#9733;</option>
                        </select>
                        <label>
                            <input name="driver-feedback-${driver_id}" type="text"
                                   placeholder="Add some feedback..." value="">
                        </label>
                    </div>

<%--                    <a> Order Rating: </a>--%>
<%--                    <div class="setting_inputs">--%>
<%--                        <select name="order-rating-${order.getId()}">--%>
<%--                            <option value="1">&#9733;</option>--%>
<%--                            <option value="2">&#9733;&#9733;</option>--%>
<%--                            <option value="3">&#9733;&#9733;&#9733;</option>--%>
<%--                            <option value="4">&#9733;&#9733;&#9733;&#9733;</option>--%>
<%--                            <option selected value="5">&#9733;&#9733;&#9733;&#9733;&#9733;</option>--%>
<%--                        </select>--%>
<%--                        <label>--%>
<%--                            <input name="order-feedback-${restaurant_id}" type="text"--%>
<%--                                   placeholder="Add some feedback..." value="">--%>
<%--                        </label>--%>
<%--                    </div>--%>

                    <c:forEach var="item" items="${food_items}">
                        <a> ${item.getName()} Rating: </a>
                        <div class="setting_inputs">
                            <select name="item-rating-${item.getId()}">
                                <option value="1">&#9733;</option>
                                <option value="2">&#9733;&#9733;</option>
                                <option value="3">&#9733;&#9733;&#9733;</option>
                                <option value="4">&#9733;&#9733;&#9733;&#9733;</option>
                                <option selected value="5">&#9733;&#9733;&#9733;&#9733;&#9733;</option>
                            </select>
                            <label>
                                <input name="item-feedback-${item.getId()}" type="text"
                                       placeholder="Add some feedback..." value="">
                            </label>
                        </div>
                    </c:forEach>

                </div>
                <button class="confirm_button" type="submit">
                    Submit Rating
                </button>

            </form>
        </div>
    </div>
</div>
</body>
</html>