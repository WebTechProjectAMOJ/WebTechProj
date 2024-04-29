<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="${pageContext.request.contextPath}/create-account">Create Account</a>
<%
    Object createdAccount = session.getAttribute("createdAccount");
    if (createdAccount != null && createdAccount.toString().equals("1")) {
%>
<h1>Welcome, login!</h1>
<%
}
    if(session.getAttribute("failedLogin") != null){
%>
    <h1>Login Failed!</h1>

<%
    }
%>
<form action="./verify-login" method="POST">
    <label for='username'>Username</label><input id='username' type="text" name="username"/>
    <label for="password">Password</label><input id="password" type="password" name="password"/>
    <button type="submit">Submit</button>
</form>
</body>
</html>
