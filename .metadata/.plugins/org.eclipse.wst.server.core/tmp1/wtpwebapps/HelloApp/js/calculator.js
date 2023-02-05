/*
    html/calculator.html
 */


document.querySelectorAll("button").forEach((btn) => {
    btn.addEventListener('click', btnClickFnc);
})


// 디스플레이에 표시하기
const display = document.getElementById('display')
function btnClickFnc(e) {
    let txt = e.target.innerText;

    if (txt == "=") {
        let allArr = display.value.split('');   // 0(48) ~ 9(57)

        for(let i = 0; i < allArr.length; i++) {
            let ascii = allArr.charCodeAt(0);

            if(ascii < 48 || ascii > 57) {
                
            }
        }

        // let result = eval(display.value);
        // console.log(result);
    }

    display.value += txt;
}