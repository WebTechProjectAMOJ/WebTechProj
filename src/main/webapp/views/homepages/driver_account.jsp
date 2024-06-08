<html>
<head>
    <title>Account Settings</title>

</head>
<jsp:include page="../includes.jsp"/>
<jsp:include page="../AccountCreations/includecsscreate.jsp"/>

<style>
    .confirm_button {
        border-color: #ffb677;
    }
</style>

<jsp:useBean id="user" scope="request" type="models.user.Driver"/>

<body>
<div class="page">
    <jsp:include page="../components/user_navbar.jsp"/>
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
                        <input type="text" class="form-control" placeholder="${user.getUsername()}"
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


                <a> Change Name: </a>
                <form action="" method="POST" id="change-name-form">
                    <div class="setting_inputs">
                        <input type="text" class="form-control" name="name" id="name" placeholder="${user.getName()}"
                               aria-label="New name">
                        <input type="hidden" name="update" value="name"/>
                        <button class="confirm_button" form="change-name-form" type="submit" id="edit_name_btn">Confirm
                            Change
                        </button>
                    </div>
                </form>


                <a> Change First Name: </a>
                <form action="" method="POST" id="change-firstname-form">
                    <div class="setting_inputs">
                        <input type="text" class="form-control" name="firstname" id="firstname"
                               placeholder="${user.getFirstName()}"
                               aria-label="New firstname">
                        <input type="hidden" name="update" value="name"/>
                        <button class="confirm_button" form="change-firstname-form" type="submit"
                                id="edit_firstname_btn">
                            Confirm Change
                        </button>
                    </div>
                </form>


                <a> Change Email: </a>
                <form action="" method="POST" id="change-email-form">
                    <div class="setting_inputs">
                        <input type="text" class="form-control" name="email" id="email" placeholder="${user.getEmail()}"
                               aria-label="New email">
                        <input type="hidden" name="update" value="email"/>
                        <button class="confirm_button" form="change-email-form" type="submit" id="edit_email_btn">
                            Confirm Change
                        </button>
                    </div>
                </form>


            </div>
            <a class="section_title" style="border:None;">Driver Preferences</a>
            <div class="divider"></div>
            <div class="grid_container" style="grid-template-columns: auto auto;">


                <a> Change Delivery Service: </a>
                <form action="" method="POST" id="change-delivery-form">
                    <div class="setting_inputs">
                        <label for="delivery_services"></label>
                        <select data-placeholder="Select Delivery Service" class="chosen-select"
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


                <a> Change Tools: </a>
                <form action="" method="POST" id="change-tool-form">
                    <div class="setting_inputs">
                        <select data-placeholder="Begin typing a name to filter..." multiple class="chosen-select"
                                name="Tools" id="Tools">
                            <option value=""></option>
                        </select>
                        <textarea name="tools" style="display: none"></textarea>
                        <button class="confirm_button" form="change-tool-form" type="submit" id="change_tool_btn">
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
    $("#Tools").chosen().change(function (){
        $("textarea[name='tools']").get(0).value = $(this).val()
    })
</script>
