document.querySelectorAll('.login-tabs > div').forEach(x => {
    if(!x.classList.contains('tab-active')){
        x.onclick = function (){
            let text = x.innerText;
            location.href = "create-account-" + text.toLowerCase();
        }
    }
})
$(".chosen-select").chosen({
    no_results_text: "Oops, nothing found!",
    width: "100%"
})

var link = window.location.origin + '/' + window.location.pathname.split('/')[1] + '/';

function checkAvailability(fieldId, key) {
    let checkval = $(fieldId).val();
    $.post(`${link}get-number`, { key: key, value: checkval })
        .done(function(data) {
            let count = Number(data);
            console.log(count);
            $(`${fieldId}`).parent().find(".count").remove();
            if (count > 0) {
                $(`${fieldId}`).parent().append(`<p class="count" style="color:red">${key.charAt(0).toUpperCase() + key.slice(1)} Already Taken!</p>`);
                $("#login-btn").prop('disabled', true);
            } else {
                $(`${fieldId}`).parent().append(`<p class="count" style="color:#1b3722">${key.charAt(0).toUpperCase() + key.slice(1)} Available</p>`);
                $("#login-btn").prop('disabled', false);
            }
        })
        .fail(function() {
            alert("error");
        });
}

$('#username').on("change", function() {
    checkAvailability('#username', 'username');
});

$('#Email').on("change", function() {
    checkAvailability('#Email', 'email');
});

if(window.location.pathname.includes('customers')){
$.post(`${link}get-all-tag-names`, {type: "allergen"})
    .done(function(data){
        let arr = data.split(',');
        console.log(arr)
        arr.forEach(str => {
            $('#Allergens').append(`<option value="${str}">${str}</option>`)
        })
        $("#Allergens").trigger("chosen:updated");
    })
}

if(window.location.pathname.includes('drivers')){
    $.get(`${link}get-all-tool-names`)
        .done(function(data){
            let arr = data.split(',');
            console.log(arr)
            arr.forEach(str => {
                $('#Tools').append(`<option value="${str}">${str}</option>`)
            })
            $("#Tools").trigger("chosen:updated");
        })
}
else {
    $.post(`${link}get-all-tag-names`, {type: "preference"})
        .done(function(data) {
            let arr = data.split(',');
            console.log(arr)
            arr.forEach(str => {
                $('#Likes').append(`<option value="${str}">${str}</option>`)
                $('#Dislikes').append(`<option value="${str}">${str}</option>`)
                $('#Tags').append(`<option value="${str}">${str}</option>`)
            })
            $("#Likes").trigger("chosen:updated");
            $("#Dislikes").trigger("chosen:updated");
            $("#Tags").trigger("chosen:updated");
        })
}

