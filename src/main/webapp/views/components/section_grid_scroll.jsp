<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="items_to_scroll" scope="request" type="java.util.HashMap"/>

<c:forEach var="key" items="${items_to_scroll.keySet()}">
    <a class="section_title">${key}</a>
    <div class="grid_container">
        <c:forEach var="item" items="${items_to_scroll.get(key)}">
            <c:set var="item" value="${item}" scope="request"/>
            <jsp:include page="./item_box.jsp"/>
        </c:forEach>
    </div>
</c:forEach>
