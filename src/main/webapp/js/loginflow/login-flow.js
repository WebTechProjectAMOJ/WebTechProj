document.querySelectorAll('.login-tabs > div').forEach(x => {
    if(!x.classList.contains('tab-active')){
        x.onclick = function (){
            let text = x.innerText;
            console.log(text)
            location.href = "CreateAccount" + text + ".jsp";
        }
    }
})
$(".chosen-select").chosen({
    no_results_text: "Oops, nothing found!",
    width: "100%"
})