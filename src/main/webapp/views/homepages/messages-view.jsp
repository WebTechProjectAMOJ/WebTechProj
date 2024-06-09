<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 09/06/2024
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <jsp:useBean id="msgs" type="java.util.ArrayList" scope="request"/>
        <c:forEach var="msg" items="${msgs}">
            <div class="message">
                <jsp:useBean id="msg" type="models.messages.Message" scope="page"/>
                <div class="msg-title">${msg.sender}</div>
                <div class="msg-body">${msg.message}</div>
            </div>
        </c:forEach>
</div>
