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
    <form action="${pageContext.request.contextPath}/create-account-drivers" method="POST">
        <div class="login-tabs">
            <div>
                <label>Customers</label>
            </div>
            <div>
                <label>Restaurants</label>
            </div>
            <div class="tab-active">
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
                <label for="delivery_services">Choose your Delivery Service:</label>
                <select data-placeholder="Select Delivery Service" class="chosen-select" name="delivery_services" id="delivery_services">
                    <option value=""></option>
                </select>
                <textarea name="delivery_service" style="display: none"></textarea>
            </div>
            <div>
                <label for="Tools">Your Tools:</label>
                <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Tools" id="Tools">
                    <option value=""></option>
                </select>
                <textarea name="tools" style="display: none"></textarea>
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
    $("#Tools").chosen().change(function (){
        $("textarea[name='tools']").get(0).value = $(this).val()
    })
</script>
</body>
</html>