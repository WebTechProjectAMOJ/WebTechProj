$('.login-tabs > div').on('click', function () {
    $(this).children('input')[0].checked = true
    $('input[name = "account-type"]').each(function () {
        $(this).parent('div').eq(0).removeClass('tab-active')
    })
    $(this).toggleClass('tab-active', true)
    let text = $(this).text().trim();
    document.querySelector('div.container').classList.remove('consumer-box', 'restaurants-box', 'driver-box');
    switch(text.toLowerCase()) {
        case "restaurants": {
            $("div.container").toggleClass("restaurants-box", true);
            break;
        }
        case "drivers": {
            $("div.container").toggleClass("driver-box", true);
            break;
        }
        default:{
            $("div.container").toggleClass("consumer-box", true);
        }
    }
})
