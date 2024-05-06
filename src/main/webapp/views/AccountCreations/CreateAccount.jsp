<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<form action="./create-account" method="POST">
    <label for='username'>Username</label><input id = 'username' type="text" name="username"/>
    <label for="password">Password</label><input id="password" type="text" name="password"/>
    <label for="name">Name</label><input id="name" type="text" name="name"/>
    <button type="submit">Submit</button>
</form>
</body>
</html>