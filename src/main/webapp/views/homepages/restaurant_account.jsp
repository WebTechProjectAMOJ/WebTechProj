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
    <jsp:include page="../components/restaurant_navbar.jsp"/>
    <div class="window">
        <jsp:include page="../components/header.jsp"/>
        <div class="popup_window"
             style="align-self: center; position: static; overflow-y: auto; padding: 1rem ; width: 100vh;">
            <a class="section_title" style="border:None;">Personal Details</a>
            <div class="divider"></div>
            <div class="grid_container" style="grid-template-columns: auto auto;">


                <a> Change Username: </a>
                <form action="" method="POST" id="change-username-form">
                    <div class="setting_inputs">
                        <input type="text" class="form-control" placeholder="New username"
                               aria-label="New username" name="username">
                        <input type="hidden" name="update" value="username"/>
                        <button class="confirm_button" form="change-username-form" type="submit" id="edit_user_btn">
                            Confirm
                            Change
                        </button>
                    </div>
                </form>


                <a> Change Password: </a>
                <form action="" method="POST" id="change-pwd-form">
                    <div class="setting_inputs">
                        <input type="text" class="form-control" placeholder="New password" id="password" name="password"
                               aria-label="New password">
                        <input type="hidden" name="update" value="password"/>
                        <button class="confirm_button" form="change-pwd-form" type="submit" id="edit_pass_btn">Confirm
                            Change
                        </button>
                    </div>
                </form>


                <a> Change Email: </a>
                <form action="" method="POST" id="change-email-form">

                    <div class="setting_inputs">
                        <input type="text" class="form-control" name="email" id="email" placeholder="New email"
                               aria-label="New email">
                        <input type="hidden" name="update" value="email"/>
                        <button class="confirm_button" form="change-email-form" type="submit" id="edit_email_btn">
                            Confirm
                            Change
                        </button>
                    </div>
                </form>

            </div>


            <a class="section_title" style="border:None;">Restaurant Preferences</a>
            <div class="divider"></div>
            <div class="grid_container" style="grid-template-columns: auto auto;">


                <a> Change Name: </a>
                <form action="" method="POST" id="change-name-form">

                    <div class="setting_inputs">
                        <input type="text" class="form-control" name="name" id="name" placeholder="New name"
                               aria-label="New name">
                        <input type="hidden" name="update" value="name"/>
                        <button class="confirm_button" form="change-name-form" type="submit" id="edit_name_btn">Confirm
                            Change
                        </button>
                    </div>
                </form>


                <a> Change Tags: </a>
                <form action="" method="POST" id="change-tags-form">
                    <div class="setting_inputs">
                        <label for="Tags"></label>
                        <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select"
                                name="tags" id="Tags">
                            <option value=""></option>
                        </select>
                        <textarea name="Tags" style="display: none"></textarea>
                        <button class="confirm_button" form="change-tags-form" type="submit" id="edit_tags_btn">Confirm
                            Change
                        </button>
                    </div>
                </form>


                <a> Change Delivery Services: </a>
                <form action="" method="POST" id="change-delivery-form">

                    <div class="setting_inputs">
                        <label for="delivery_services"></label>
                        <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select"
                                name="delivery_services" id="delivery_services">
                            <option value=""></option>
                        </select>
                        <textarea name="delivery_service" style="display: none"></textarea>
                        <button class="confirm_button" form="change-delivery-form" type="submit"
                                id="change_delivery_btn">
                            Confirm Change
                        </button>
                    </div>
                </form>


                <a> Change Address: </a>
                <form action="" method="POST" id="change-address-form">
                    <div class="setting_inputs">
                        <jsp:include page="../AccountCreations/googleautocomplete.html"/>
                        <button class="confirm_button" form="change-address-form" type="submit" id="change_add_btn">
                            Confirm
                            Change
                        </button>
                    </div>
                </form>


            </div>
        </div>
    </div>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/loginflow/login-flow.js">
</script>
<script>
    $("#Tags").chosen().change(function () {
        $("textarea[name='Tags']").get(0).value = $(this).val()
    })
    $("#delivery_services").chosen().change(function () {
        $("textarea[name='delivery_service']").get(0).value = $(this).val()
    })
</script>
