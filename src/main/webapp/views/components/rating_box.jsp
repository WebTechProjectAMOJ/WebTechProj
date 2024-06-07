<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="item" scope="request" class="models.ui_util.RatingBoxUi"/>

<div class="rating_container border-rating">
    <div>
        <p>${item.content}</p>
        <c:forEach var="i" begin="1" end="${item.rating}">
            &#9733;
        </c:forEach>
    </div>
    <div>
        <p style="display: none" class="id-data">${item.action_url}</p>
        <a>${item.sub_name}</a>
    </div>
</div>