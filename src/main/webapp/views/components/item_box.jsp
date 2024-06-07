<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="item" scope="request" class="models.ui_util.ItemBoxUi"/>


<c:choose>
    <c:when test="${item.action_url != ''}">
        <button
                class="button-transparent"
                onclick="window.location.replace('${pageContext.request.contextPath}/${item.action_url}')"
        >
            <div class="box_container">
                <div class="box"><img src="${item.photo_url}"/></div>
                <h3>${item.name}</h3>
                <p style="display: none" class="id-data">${item.action_url}</p>
                <a>${item.sub_name}</a>
            </div>
        </button>
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



