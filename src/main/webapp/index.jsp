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
<form action="./test-connection" method="POST">
    <label for='no'>Enter Digit</label><input id = 'no' type="text" name="no"/>
    <button type="submit">Submit</button>
</form>
</body>
</html>