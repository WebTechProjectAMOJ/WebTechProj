let link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';

if(window.location.href.includes("add-tool")) {
    $.get(`${link}get-all-tool-names`)
        .done(function (data) {
            $('#name').on("change", function () {
                $(this).parent().find(".count").remove();
                if (data.includes($(this).val().toLowerCase())) {
                    $(this).parent().append("<p class='count' style=\"color:red\">Name Not available</p>")
                    $("#login-btn").prop('disabled', true);
                    $("#login-btn").toggleClass("btn-disabled", true)
                } else {
                    $(this).parent().append("<p class='count' style=\"color:#1b3722\">Name Available</p>")
                    $("#login-btn").prop('disabled', false);
                    $("#login-btn").toggleClass("btn-disabled", false)
                }
            })
        })
}

if(window.location.href.includes("add-tag")) {
    $.post(`${link}get-all-tag-names`, {type: $("#type").val()})
        .done(function (data) {
            $('#name').on("change", function () {
                console.log($("#type").val())
                $(this).parent().find(".count").remove();
                if($("#type").val() == null || $("#type").val() == ""){
                    $(this).parent().append("<p class='count' style=\"color:red\">Select a type!</p>")
                    $("#login-btn").prop('disabled', true);
                    $("#login-btn").toggleClass("btn-disabled", true)
                }
                else if (data.includes($(this).val().toLowerCase())) {
                    $(this).parent().append("<p class='count' style=\"color:red\">Name Not available</p>")
                    $("#login-btn").prop('disabled', true);
                    $("#login-btn").toggleClass("btn-disabled", true)
                } else {
                    $(this).parent().append("<p class='count' style=\"color:#1b3722\">Name Available</p>")
                    $("#login-btn").prop('disabled', false);
                    $("#login-btn").toggleClass("btn-disabled", false)
                }
            })
        })
}


