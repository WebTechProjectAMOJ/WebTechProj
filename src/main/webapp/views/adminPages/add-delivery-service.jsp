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
<jsp:include page="header2.jsp"/>
    <form method="POST" action="${pageContext.request.contextPath}/add-delivery-service-admin">
        <div id="login-cloud">
            <div>
                <label for="name">Name:</label>
                <input id="name" type="text" name="name" required/>
            </div>
            <div>
                <label for="fee">Fee:</label>
                <input id="fee" type="number" name="fee" value="0.0" step="0.1" required/>
            </div>
            <button type="submit" id="login-btn">Submit</button>
        </div>
    </form>
<jsp:include page="footer.jsp"/>