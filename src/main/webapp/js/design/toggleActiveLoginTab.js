// $('.login-tabs > div').on('click', function () {
//     $(this).children('input')[0].checked = true
//     $('input[name = "account-type"]').each(function () {
//         $(this).parent('div').eq(0).removeClass('tab-active')
//     })
//     $(this).toggleClass('tab-active')
// })
document.querySelectorAll('.login-tabs > div').forEach(x => {
    if(!x.classList.contains('tab-active')){
        x.onclick = function (){
            let text = x.innerText.toLowerCase();
            document.getElementById('login-cloud').classList.remove('consumer-box', 'restaurants-box', 'driver-box');
            if (text === 'restaurants') {
                document.getElementById('login-cloud').classList.add('restaurants-box');
            } else if (text === 'drivers') {
                document.getElementById('login-cloud').classList.add('driver-box');
            } else {
                document.getElementById('login-cloud').classList.add('consumer-box');
            }
            document.querySelector('.tab-active').classList.remove('tab-active');
            x.classList.add('tab-active');
        }
    }
});

