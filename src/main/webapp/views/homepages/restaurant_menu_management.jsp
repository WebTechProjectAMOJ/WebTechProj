<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu Management</title>

    <link rel="stylesheet" href="../../css/login-page-style.css">
    <link rel="stylesheet" href="../../css/style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>

<jsp:include page="../includes.jsp"/>
<jsp:useBean id="food_items_to_scroll" scope="request" type="java.util.HashMap"/>

<body>
<div class="page">
    <jsp:include page="../components/restaurant_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/header.jsp"/>
        <c:set var="items_to_scroll" value="${food_items_to_scroll}" scope="request"/>
        <jsp:include page="../components/section_grid_scroll.jsp"/>
        <div class="footer">
            <button type="button" class="confirm_button" style="background-color: #B5C964" onclick="openCat()"> Add Menu
                Category
            </button>
            <button type="button" class="confirm_button" style="background-color: #B5C964" onclick="openItem()"> Add
                Menu Item
            </button>
        </div>

        <div class="add_popup" id="add-item">
            <form class="form_add" method="post">
                <h3 style="align-self: center">Add Menu Item</h3>
                <label>Item Name:
                    <input type="text" name="item_name" required/>
                </label>

                <label>Cost:
                    <input type="number" name="item_price" required/>
                </label>

                <label>Category:
                    <select id="myCategories">
                        <option value="categories"> option1</option>
                    </select>
                </label>
                <label>Tags:
                    <select id="myTags">
                        <option value="tags"> option1</option>
                    </select>
                </label>
                <div class="footer">
                    <button type="submit" class="confirm_button" style="background-color: #B5C964"> Add Item</button>
                    <button type="button" class="confirm_button"
                            style="background-color: indianred; border-color: indianred;" onclick="closePopup()"> Cancel
                    </button>
                </div>
            </form>
        </div>

        <div class="add_popup" id="add-category">
            <form class="form_add" method="post">
                <h3 style="align-self: center">Add Category</h3>
                <label>Category Name:
                    <input type="text" name="item_name" required/>
                </label>
                <div class="footer">
                    <button type="submit" class="confirm_button" style="background-color: #B5C964"> Add Category
                    </button>
                    <button type="button" class="confirm_button"
                            style="background-color: indianred; border-color: indianred;" onclick="closePopup()"> Cancel
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

<script>
    function openItem() {
        document.getElementById("add-item").style.display = "block";
    }

    function openCat() {
        document.getElementById("add-category").style.display = "block";
    }

    function closePopup() {
        document.getElementById("add-item").style.display = "none";
        document.getElementById("add-category").style.display = "none";
    }
</script>

