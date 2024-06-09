<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:forEach var="key" items="${requestScope.get('items_to_scroll').keySet()}">
    <a class="section_title">${key}</a>
    <div class="grid_container">
        <c:forEach var="item" items="${requestScope.get('items_to_scroll').get(key)}">
            <c:set var="item" value="${item}" scope="request"/>
            <c:if test="${not empty param.type and param.type.equals('rating')}">
                <jsp:include page="./rating_box.jsp"/>
            </c:if>
            <c:if test="${empty param.type}">
                <c:if test="${empty param.query or fn:containsIgnoreCase(item.name, param.query) or fn:containsIgnoreCase(item.sub_name, param.query)}">
                    <jsp:include page="./item_box.jsp"/>
                </c:if>
            </c:if>
        </c:forEach>
    </div>
</c:forEach>
