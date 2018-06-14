document.getElementById("profileLogin").textContent="IHOR";

$(document).ready(function() {
    var user = JSON.parse(localStorage.getItem("user"))
    if (user.login){
        document.getElementById("profileLogin").textContent = user.login;
    }else {
        document.getElementById("profileLogin").textContent = "   ---   ";
    }

    if (user.birthDay){
        document.getElementById("profileDateOfBirth").textContent = user.birthDay;
    }else {
        document.getElementById("profileDateOfBirth").textContent = "   ---   ";
    }

    if (user.phone){
        document.getElementById("profilePhone").textContent = user.phone;
    }else {
        document.getElementById("profilePhone").textContent = "   ---   ";
    }


    if (user.name){
        document.getElementById("profileName").textContent = user.name;
    }else {
        document.getElementById("profileName").textContent = "   ---   ";
    }

});