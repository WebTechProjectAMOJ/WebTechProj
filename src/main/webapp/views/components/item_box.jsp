<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="item" scope="request" class="models.ui_util.ItemBoxUi"/>

<div class="box_container">
    <div class="box"><img src="${item.photo_url}"/> </div>
    <h3>${item.name}</h3>
    <p style="display: none" class="id-data">${item.action_url}</p>
    <a>${item.sub_name}</a>
</div>
