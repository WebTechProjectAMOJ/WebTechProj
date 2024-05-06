<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="../../css/login-page-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div id="logo-login">
        <img id="login-logo-img" src="${pageContext.request.contextPath}/assets/food-dispatch-logo.png" alt="logo">
    </div>
    <form action="${pageContext.request.contextPath}/create-account" method="POST">
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
                <input id='username' type="text" name="username"/>
            </div>
            <div>
                <label for="password">Password:</label>
                <input id="password" type="password" name="password"/>
            </div>
            <div>
                <label for="name">Name:</label>
                <input id="name" type="text" name="name"/>
            </div>
            <div>
                <label for="Email">Email:</label>
                <input id="Email" type="text" name="Email"/>
            </div>
            <div>
                <label for="Likes">Likes:</label>
                <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Likes" id="Likes">
                    <option value=""></option>
                    <option>American Black Bear</option>
                    <option>Asiatic Black Bear</option>
                    <option>Brown Bear</option>
                    <option>Giant Panda</option>
                    <option>Sloth Bear</option>
                    <option>Sun Bear</option>
                    <option>Polar Bear</option>
                    <option>Spectacled Bear</option>
                </select>
            </div>
            <div>
                <label for="Dislikes">Dislike:</label>
                <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Dislikes" id="Dislikes">
                    <option value=""></option>
                    <option>American Black Bear</option>
                    <option>Asiatic Black Bear</option>
                    <option>Brown Bear</option>
                    <option>Giant Panda</option>
                    <option>Sloth Bear</option>
                    <option>Sun Bear</option>
                    <option>Polar Bear</option>
                    <option>Spectacled Bear</option>
                </select>
            </div>
            <div>
                <label for="Allergens">Allergens:</label>
                <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Likes" id="Allergens">
                    <option value=""></option>
                    <option>American Black Bear</option>
                    <option>Asiatic Black Bear</option>
                    <option>Brown Bear</option>
                    <option>Giant Panda</option>
                    <option>Sloth Bear</option>
                    <option>Sun Bear</option>
                    <option>Polar Bear</option>
                    <option>Spectacled Bear</option>
                </select>
            </div>
            <div>
                <jsp:include page="../../components/googleautocomplete.html"/>
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
</body>
</html>