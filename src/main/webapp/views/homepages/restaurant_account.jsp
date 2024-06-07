<%--
  Created by IntelliJ IDEA.
  User: margotrichez
  Date: 06/05/2024
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Settings</title>

</head>
<jsp:include page="../includes.jsp"/>
<jsp:include page="../AccountCreations/includecsscreate.jsp"/>

<style>
    .confirm_button {
        border-color: #a2a8d3;
    }
</style>

<body>
<div class="page">
    <jsp:include page="../components/user_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/header.jsp" />
        <div class="popup_window" style="align-self: center; position: static; overflow-y: auto; padding: 1rem ; width: 100vh;">
            <a class="section_title" style="border:None;">Personal Details</a>
            <div class="divider"></div>
            <div class="grid_container" style="grid-template-columns: auto auto;">
                <a> Change Username: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-username-form">
                        <input type="text" class="form-control" placeholder="New username"
                               aria-label="New username" name="username">
                        <input type="hidden" name="update" value="username"/>
                    </form>
                    <button class="confirm_button" form="change-username-form" type="button" id="edit_user_btn">Confirm Change</button>
                </div>
                <a> Change Password: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-pwd-form">
                        <input type="text" class="form-control" placeholder="New password" id="password" name="password"
                               aria-label="New password">
                        <input type="hidden" name="update" value="password"/>
                    </form>
                    <button class="confirm_button" form="change-pwd-form" type="button" id="edit_pass_btn">Confirm Change</button>
                </div>
                <a> Change Email: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-email-form">
                        <input type="text" class="form-control" name="email" id="email" placeholder="New email"
                               aria-label="New email">
                        <input type="hidden" name="update" value="email"/>
                    </form>
                    <button class="confirm_button" form="change-email-form" type="button" id="edit_email_btn">Confirm Change</button>
                </div>
            </div>
            <a class="section_title" style="border:None;">Restaurant Preferences</a>
            <div class="divider"></div>
            <div class="grid_container" style="grid-template-columns: auto auto;">
                <a> Change Name: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-name-form">
                        <input type="text" class="form-control" name="name" id="name" placeholder="New name"
                               aria-label="New name">
                        <input type="hidden" name="update" value="name"/>
                    </form>
                    <button class="confirm_button" form="change-name-form" type="button" id="edit_name_btn">Confirm
                        Change
                    </button>
                </div>
                <a> Change Tags: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-tags-form">
                        <label for="Tags"></label>
                        <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select"
                                name="tags" id="Tags">
                            <option value=""></option>
                        </select>
                        <textarea name="Tags" style="display: none"></textarea>
                    </form>
                    <button class="confirm_button" form="change-tags-form" type="button" id="edit_tags_btn">Confirm
                        Change
                    </button>
                </div>
                <a> Change Delivery Services: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-delivery-form">
                        <label for="delivery_services"></label>
                        <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="delivery_services" id="delivery_services">
                            <option value=""></option>
                        </select>
                        <textarea name="delivery_service" style="display: none"></textarea>
                    </form>
                    <button class="confirm_button" form="change-delivery-form" type="button" id="change_delivery_btn">Confirm Change
                    </button>
                </div>
                <a> Change Address: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-address-form">
                        <jsp:include page="../AccountCreations/googleautocomplete.html"/>
                    </form>
                    <button class="confirm_button" form="change-address-form" type="button" id="change_add_btn">Confirm Change
                    </button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/loginflow/login-flow.js">
</script>
<script>
    $("#Likes").chosen().change(function () {
        $("textarea[name='likes']").get(0).value = $(this).val()
    })
    $("#Dislikes").chosen().change(function () {
        $("textarea[name='dislikes']").get(0).value = $(this).val()
    })
    $("#Allergens").chosen().change(function () {
        $("textarea[name='allergens']").get(0).value = $(this).val()
    })
</script>
