<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="item" scope="request" class="models.ui_util.ItemBoxUi"/>

<div class="box_container" onclick="openBox()">
    <div class="box"></div>
    <h3>Element Name: ${item.name}</h3>
    <a>${item.sub_name}</a>
</div>
