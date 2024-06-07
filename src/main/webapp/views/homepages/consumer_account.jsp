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
<body>
<div class="page">
    <jsp:include page="../components/user_navbar.jsp" />
    <div class="window">
        <jsp:include page="../components/header.jsp" />
        <div class="popup_window" style="align-self: center; position: static; overflow-y: auto; padding: 1rem ; width: 100vh;">
            <a class="section_title" style="border:None;">Username & Password</a>
            <div class="divider"></div>
            <div class="grid_container" style="grid-template-columns: auto auto;">
                <a> Change Username: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-username-form">
                        <input type="text" class="form-control" placeholder="New username"
                               aria-label="New username" aria-describedby="button-addon2" name="username">
                        <input type="hidden" name="update" value="username"/>
                    </form>
                    <button class="confirm_button" form="change-username-form" type="button" id="edit_user_btn">Confirm Change</button>
                </div>
                <a> Change Password: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-pwd-form">
                        <input type="text" class="form-control" placeholder="New password" id="password" name="password"
                               aria-label="New password" aria-describedby="button-addon2">
                        <input type="hidden" name="update" value="password"/>
                    </form>
                    <button class="confirm_button" form="change-pwd-form" type="button" id="edit_pass_btn">Confirm Change</button>
                </div>
                <a> Change Name: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-name-form">
                        <input type="text" class="form-control" name="name" id="name" placeholder="New name"
                               aria-label="New name" aria-describedby="button-addon2">
                        <input type="hidden" name="update" value="name"/>
                    </form>
                    <button class="confirm_button" form="change-name-form" type="button" id="edit_name_btn">Confirm Change</button>
                </div>
                <a> Change First Name: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-firstname-form">
                        <input type="text" class="form-control" name="firstname" id="firstname" placeholder="New first name"
                               aria-label="New firstname" aria-describedby="button-addon2">
                        <input type="hidden" name="update" value="name"/>
                    </form>
                    <button class="confirm_button" form="change-firstname-form" type="button" id="edit_firstname_btn">Confirm Change</button>
                </div>
                <a> Change Email: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-email-form">
                        <input type="text" class="form-control" name="email" id="email" placeholder="New email"
                               aria-label="New email" aria-describedby="button-addon2">
                        <input type="hidden" name="update" value="email"/>
                    </form>
                    <button class="confirm_button" form="change-email-form" type="button" id="edit_email_btn">Confirm Change</button>
                </div>
            </div>
            <a class="section_title" style="border:None;">Food Preferences</a>
            <div class="divider"></div>
            <div class="grid_container" style="grid-template-columns: auto auto;">
                <a> Change Likes: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-likes-form">
                        <label for="Likes"></label>
                        <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Likes" id="Likes">
                            <option value=""></option>
                        </select>
                        <textarea name="likes" style="display: none"></textarea>
                    </form>
                    <button class="confirm_button" form="change-likes-form" type="button" id="edit_likes_btn">Confirm Change</button>
                </div>
                <a> Change Dislikes: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-dislike-form">
                        <label for="Dislikes"></label>
                        <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Dislikes" id="Dislikes" style="width:20rem;">
                            <option value=""></option>
                        </select>
                        <textarea name="dislikes" style="display: none"></textarea>
                    </form>
                    <button class="confirm_button" form="change-dislike-form" type="button" id="edit_dislikes_btn">Confirm Change</button>
                </div>
                <a> Change Allergens: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="change-allergens-form">
                        <label for="Allergens"></label>
                        <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select" name="Allergens" id="Allergens" style="width:20rem;">
                            <option value=""></option>
                        </select>
                        <textarea name="allergens" style="display: none"></textarea>
                    </form>
                    <button class="confirm_button" form="change-allergens-form" type="button" id="edit_allergens_btn">
                        Confirm Change
                    </button>
                </div>
            </div>
            <a class="section_title" style="border:None;">Addresses</a>
            <div class="divider"></div>
            <div class="grid_container" style="grid-template-columns: auto auto;">
                <a> Add address: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="new-address-form">
                        <jsp:include page="../AccountCreations/googleautocomplete.html"/>
                    </form>
                    <button class="confirm_button" form="new-address-form" type="button" id="new_add_btn">Add</button>
                </div>
                <a> Remove address: </a>
                <div class="setting_inputs">
                    <form action="" method="POST" id="delete-address-form">
                        <label for="address"></label>
                        <select class="address_select" name="registered_addresses" id="address">
                            <option value="">Select...</option>
                            <option value=""></option>
                        </select>
                    </form>
                    <button class="confirm_button" style="border-color: indianred;" form="delete-address-form"
                            type="button" id="delete_add_btn">Delete
                    </button>
                </div>
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
