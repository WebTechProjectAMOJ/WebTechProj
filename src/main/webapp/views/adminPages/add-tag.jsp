<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 22/05/2024
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<jsp:useBean id="tagtypes" scope="request" type="java.util.ArrayList"/>
<jsp:include page="header2.jsp"/>
    <form method="POST" action="${pageContext.request.contextPath}/add-tag-admin">
        <div id="login-cloud">
            <div>
                <label for="type">Type:</label>
                <select name="type" id="type">
                    <option selected="selected" value="" disabled>Select a Type</option>
                    <c:forEach items="${tagtypes}" var="tagtype">
                        <option value="${tagtype}">${tagtype}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="name">Name:</label>
                <input id="name" type="text" name="name" required/>
            </div>
            <button type="submit" id="login-btn">Submit</button>
        </div>
    </form>
<jsp:include page="footer.jsp"/>