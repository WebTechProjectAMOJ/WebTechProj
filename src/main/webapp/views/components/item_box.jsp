<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="item" scope="request" class="models.ui_util.ItemBoxUi"/>
<c:set var="url" value="${pageContext.request.requestURL}" scope="request"/>
<c:set var="history"
       value="http://localhost:8080${pageContext.request.contextPath}/views/homepages/consumer_history.jsp"
       scope="request"/>


<c:choose>
    <c:when test="${url == history}">
        <form method="POST" action="${pageContext.request.contextPath}/customer-review?id=${item.sub_name}">
            <div class="box_container">
                <div class="box"><img src="${item.photo_url}"/></div>
                <h3>${item.name}</h3>
                <p style="display: none" class="id-data">${item.action_url}</p>
                <a>${item.sub_name}</a>
                <button type="submit" class="confirm_button">
                    Rate Order
                </button>
            </div>
        </form>
    </c:when>
    <c:when test="${url != history and item.getType() == 'order'}">
        <form method="GET" action="${pageContext.request.contextPath}/order-details">
            <input name="orderid" value="${item.sub_name}" class="disappear"/>
            <button type="submit" class="button-transparent">
                <div class="box_container">
                    <div class="box"><img src="${item.photo_url}"/></div>
                    <h3>${item.name}</h3>
                    <p style="display: none" class="id-data">${item.action_url}</p>
                    <a>${item.sub_name}</a>
                </div>
            </button>
        </form>
    </c:when>

    <c:otherwise>
        <div class="box_container">
            <div class="box"><img src="${item.photo_url}"/></div>
            <h3>${item.name}</h3>
            <p style="display: none" class="id-data">${item.action_url}</p>
            <a>${item.sub_name}</a>
        </div>
    </c:otherwise>
</c:choose>
