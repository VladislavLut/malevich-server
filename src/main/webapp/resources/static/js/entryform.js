$(document).ready(function() {
    //Скрыть PopUp при загрузке страницы
    if (isLogedIn()){document.getElementById("entryText").textContent="Профиль";}
    PopUpHide();
});
//Функция отображения PopUp
function PopUpShow() {
    $("#popup1").show();
}
//Функция скрытия PopUp
function PopUpHide() {
    $("#popup1").hide();
}

function isLogedIn() {
    if (JSON.parse(localStorage.getItem("user")) && !(JSON.parse(localStorage.getItem("user")).error))
        return true;
    else return false;
}



