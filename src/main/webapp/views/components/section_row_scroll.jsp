<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:forEach var="key" items="${requestScope.get('items_to_scroll').keySet()}">
    <a class="section_title">${key}</a>
    <div class="grid_row_container">
        <c:forEach var="item" items="${requestScope.get('items_to_scroll').get(key)}">
            <c:set var="item" value="${item}" scope="request"/>
            <jsp:include page="./item_box.jsp"/>
        </c:forEach>
    </div>
</c:forEach>
