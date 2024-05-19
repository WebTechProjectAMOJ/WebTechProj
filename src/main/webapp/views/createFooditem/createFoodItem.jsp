<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 18/05/2024
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create A Food Item</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login-page-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
</head>
<body>
<%
    session=request.getSession(false);
    if(session.getAttribute("accountType")!="restaurant")
    {
%>
<script>alert("You're not a restaurant!");
</script>
<%
        response.sendRedirect("index.jsp");
    }
%>
<div class="container">
    <div id="logo-login">
        <img id="login-logo-img" src="${pageContext.request.contextPath}/assets/food-dispatch-logo.png" alt="logo">
    </div>
    <form action="${pageContext.request.contextPath}/create-food-item" method="POST" enctype="multipart/form-data">
        <div id="login-cloud">
            <div>
                <label for="name">Name:</label>
                <input id="name" type="text" name="name" required/>
            </div>
            <div>
                <label for="price">Price:</label>
                <input id="price" type="number" name="price" min="0.01" max="100" step="0.01" value="0.01"/>
            </div>
            <div>
                <label for="imageFile">Upload a Picture:</label>
                <input type="file" id="imageFile" name="imageFile" accept="image/*" style="vertical-align: middle; padding-top: 10px"/>
            </div>
            <div>
                <label for="Likes">Tags:</label>
                <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Likes" id="Likes">
                    <option value=""></option>
                    <textarea name="likes" style="display: none"></textarea>
                </select>
            </div>
            <div>
                <label for="Tools">Required Tools:</label>
                <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Tools" id="Tools">
                    <option value=""></option>
                </select>
                <textarea name="tools" style="display: none"></textarea>
            </div>
            <button type="submit" id="login-btn">Submit</button>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/loginflow/login-flow.js">
</script>
<script>
    $("#Likes").chosen().change(function (){
        $("textarea[name='likes']").get(0).value = $(this).val()
    })
    $("#Tools").chosen().change(function (){
        $("textarea[name='tools']").get(0).value = $(this).val()
    })
</script>
</body>
</html>
