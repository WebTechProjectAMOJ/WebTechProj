<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <jsp:include page="includecsscreate.jsp"/>
</head>
<body>
<div class="container">
    <div id="logo-login">
        <img id="login-logo-img" src="${pageContext.request.contextPath}/assets/food-dispatch-logo.png" alt="logo">
    </div>
    <form action="${pageContext.request.contextPath}/create-account-customers" method="POST">
        <div class="login-tabs">
            <div class="tab-active">
                <label>Customers</label>
            </div>
            <div>
                <label>Restaurants</label>
            </div>
            <div>
                <label>Drivers</label>
            </div>
        </div>
        <div id="login-cloud">
            <div>
                <label for='username'>Username:</label>
                <input id='username' type="text" name="username" required/>
            </div>
            <div>
                <label for="password">Password:</label>
                <input id="password" type="password" name="password" required/>
            </div>
            <div>
                <label for="name">Name:</label>
                <input id="name" type="text" name="name" required/>
            </div>
            <div>
                <label for="firstname">First Name:</label>
                <input id="firstname" type="text" name="firstname"/>
            </div>
            <div>
                <label for="Email">Email:</label>
                <input id="Email" type="email" name="Email" required/>
            </div>
            <div>
                <label for="Likes">Likes:</label>
                <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Likes" id="Likes">
                    <option value=""></option>
                    <textarea name="likes" style="display: none"></textarea>
                </select>
            </div>
            <div>
                <label for="Dislikes">Dislike:</label>
                <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Dislikes" id="Dislikes">
                    <option value=""></option>
                </select>
                <textarea name="dislikes" style="display: none"></textarea>
            </div>
            <div>
                <label for="Allergens">Allergens:</label>
                <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Allergens" id="Allergens">
                    <option value=""></option>
                </select>
                <textarea name="allergens" style="display: none"></textarea>
            </div>
            <div>
                <jsp:include page="googleautocomplete.html"/>
            </div>
            <button type="submit" id="login-btn">Submit</button>
            <span class="register-here">
            <a href="${pageContext.request.contextPath}/create-account">Not Registered? Click Here</a>
            </span>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/loginflow/login-flow.js">
</script>
<script>
    $("#Likes").chosen().change(function (){
        $("textarea[name='likes']").get(0).value = $(this).val()
    })
    $("#Dislikes").chosen().change(function (){
        $("textarea[name='dislikes']").get(0).value = $(this).val()
    })
    $("#Allergens").chosen().change(function (){
        $("textarea[name='allergens']").get(0).value = $(this).val()
    })
</script>
</body>
</html>