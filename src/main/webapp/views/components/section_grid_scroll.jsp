<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="categories" scope="request" type="java.util.Arrays"/>

<c:forEach var="cat" items="${categories}">
    <a class="section_title">${cat}</a>
    <div class="grid_container">
        <jsp:include page="./item_box.jsp" />
    </div>
</c:forEach>



