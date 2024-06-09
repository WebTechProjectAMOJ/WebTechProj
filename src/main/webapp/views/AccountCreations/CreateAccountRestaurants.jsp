<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <jsp:include page="includecsscreate.jsp"/>
</head>
<body>
<div class="container restaurants-box" >
    <div id="logo-login">
        <img id="login-logo-img" src="${pageContext.request.contextPath}/assets/food-dispatch-logo.png" alt="logo">
    </div>
    <form action="${pageContext.request.contextPath}/create-account-restaurants" method="POST">
        <div class="login-tabs">
            <div>
                <label>Customers</label>
            </div>
            <div class="tab-active">
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
                <label for="name">Restaurant Name:</label>
                <input id="name" type="text" name="name" required/>
            </div>
            <div>
                <label for="Email">Email:</label>
                <input id="Email" type="email" name="Email" required/>
            </div>
            <div>
                <label for="Tags">Your Tags:</label>
                <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="tags" id="Tags">
                    <option value=""></option>
                </select>
                <textarea name="Tags" style="display: none"></textarea>
            </div>
            <div>
                <label for="delivery_services">Choose your Delivery Services:</label>
                <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="delivery_services" id="delivery_services">
                    <option value=""></option>
                </select>
                <textarea name="delivery_service" style="display: none"></textarea>
            </div>
            <div>
                <jsp:include page="googleautocomplete.html"/>
            </div>
            <button type="submit" id="login-btn">Submit</button>
            <span class="register-here">
            <a href="${pageContext.request.contextPath}/">Already Have an Account? Click Here</a>
            </span>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/loginflow/login-flow.js">
</script>
<script>
    $("#Tags").chosen().change(function (){
        $("textarea[name='Tags']").get(0).value = $(this).val()
    })
    $("#delivery_services").chosen().change(function (){
        $("textarea[name='delivery_service']").get(0).value = $(this).val()
    })
</script>
</body>
</html>