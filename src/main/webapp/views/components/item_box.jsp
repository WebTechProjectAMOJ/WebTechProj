<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="item" scope="request" class="models.order.Order"/>

<div class="box_container">
    <div class="box"></div>
    <h3>Element Name:</h3>
    <a>${item.id}</a>
</div>
