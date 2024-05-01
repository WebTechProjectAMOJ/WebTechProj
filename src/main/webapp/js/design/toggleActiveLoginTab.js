$('.login-tabs > div').on('click', function () {
    $(this).children('input')[0].checked = true
    $('input[name = "account-type"]').each(function () {
        $(this).parent('div').eq(0).removeClass('tab-active')
    })
    $(this).toggleClass('tab-active')
})
