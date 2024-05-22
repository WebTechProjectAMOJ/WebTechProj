<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
</head>
<jsp:include page="./views/includes.jsp" />
<body>

<div class="container">
    <div id="logo-login">
        <img id="login-logo-img" src="assets/food-dispatch-logo.png" alt="logo">
    </div>
    <c:if test="${sessionScope.message != null}">
        <h3 style="background: rgba(181, 201, 100, 0.6); padding: 1em; border: #1b3722 2px solid; border-radius:2em;"> ${sessionScope.message}</h3>
        <c:remove var="message"/>
    </c:if>
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
            <c:if test="${sessionScope.errorMessage != null}">
                <p style="color: red; width: 80%; margin:auto; text-align: center">${sessionScope.errorMessage}</p>
                <c:remove var="errorMessage"/>
            </c:if>
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
            <a href="${pageContext.request.contextPath}/create-account-customers">Not Registered? Click Here</a>
            </span>
        </div>
    </form>
</div>
<script src="js/design/toggleActiveLoginTab.js">
</script>
</body>
</html>
