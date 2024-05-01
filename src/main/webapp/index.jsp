<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
</head>
<jsp:include page="views/includes.jsp" />
<body>

<div class="container">
    <div id="logo-login">
        <img id="login-logo-img" src="assets/food-dispatch-logo.png" alt="logo">
    </div>
        <%
    Object createdAccount = session.getAttribute("createdAccount");
    if (createdAccount != null && createdAccount.toString().equals("1")) {
%>
    <h3 style="background: rgba(181, 201, 100, 0.6); padding: 1em; border: #1b3722 2px solid; border-radius:2em;"> Welcome to Food Dispatch, Your Account has been created! Please Log in!</h3>
<%
        }
%>
    <form action="./verify-login" method="POST">
        <div class="login-tabs">
            <div class="tab-active">
                <input class="login-tab" type="radio" id="Customers" name="account-type" value="Customers" checked/>
                <label for="Customers">Customers</label>
            </div>
            <div>
                <input class="login-tab" type="radio" id="contactChoice2" name="account-type" value="Restaurants" />
                <label for="contactChoice2">Restaurants</label>
            </div>
            <div>
                <input class="login-tab" type="radio" id="contactChoice3" name="account-type" value="Drivers" />
                <label for="contactChoice3">Drivers</label>
            </div>
        </div>
        <div id="login-cloud">
            <%
                if(session.getAttribute("failedLogin") != null){
            %>
                <p style="color: red; width: 80%; margin:auto; text-align: center"> Invalid Credentials</p>
            <%
                }
            %>
            <div>
                <label for='username'>Username:</label>
                <input id='username' type="text" name="username"/>
            </div>
            <div>
                <label for="password">Password:</label>
                <input id="password" type="password" name="password"/>
            </div>
        <button type="submit" id="login-btn">Submit</button>
            <span class="register-here">
            <a href="${pageContext.request.contextPath}/create-account">Not Registered? Click Here</a>
            </span>
        </div>
    </form>
</div>
<script src="js/design/toggleActiveLoginTab.js">
</script>
</body>
</html>
