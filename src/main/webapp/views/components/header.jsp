<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="application" class="models.user.User"/>

<div class="header_row">
  <div class="header_text_container">
    <h1>${user.name}</h1>
    <h2>View Name</h2>
  </div>
  <img src="${pageContext.request.contextPath}/assets/food-dispatch-logo.png" alt="logo">
</div>
