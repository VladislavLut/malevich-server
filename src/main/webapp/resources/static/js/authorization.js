$("document").ready(function () {
    isSidCreate();
});

function isSidCreate(){
    if(document.cookie.indexOf("sid") < 0){
        const a = CreateSession();
    }
}
async function CreateSession() {
    try{
        await StartSession();
    }
    catch (error) {
        console.error(error);
    }
}

function StartSession() {
    return fetch("/session/start",{credentials: "same-origin"}).then(() => console.warn("Cookie ok"));
}

function LogIn(){
    var login = document.getElementById("entrylogin").value;
    var password = document.getElementById("entry-password").value;
    getUser(login, password);
}

function getUser(login, password) {
    fetch('/login', {method: "POST", headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({login, password})}
    ).then(res => res.json()).then(res => {
        localStorage.setItem("user", JSON.stringify(res));
}).then(continueLogIning).catch(console.error);
}

function isLogedIn() {
    if (JSON.parse(localStorage.getItem("user")) && !(JSON.parse(localStorage.getItem("user")).error))
        return true;
    else return false;
}

function continueLogIning() {
    if(isLogedIn()) {
        PopUpHide();
        document.getElementById("entryText").textContent="Профиль";
        document.getElementById("entrylogin").value = "";
        document.getElementById("entry-password").value = "";
    }
    else {
        document.getElementById("entrylogin").value = "";
        document.getElementById("entry-password").value = "";
        alert("Неверный пароль или логин, попробуйте еще раз!");
    }
}

function showModalWindowOrProfile() {
    if (isLogedIn()) {
        showProfile();
        document.getElementById("entryText").textContent="Профиль";
    } else {
        PopUpShow();
        document.getElementById("entryText").textContent="Вход";
    }
}

function showProfile() {
    var location = window.location.protocol+'//'+window.location.hostname+(window.location.port ? ':'+window.location.port: '');

    document.location.href = location + "/profile#";
}

function PopUpShow() {
    $("#popup1").show();
}

function logOut() {
    if (confirm("Вы уверены, что хотите выйти?")) {
        var location = window.location.protocol + '//' + window.location.hostname + (window.location.port ? ':' + window.location.port : '');
        location += "/logout";
        fetch("/logout").then(() => console.log(JSON.parse(localStorage.getItem("user"))));

        localStorage.removeItem("user");
        var location = window.location.protocol + '//' + window.location.hostname + (window.location.port ? ':' + window.location.port : '');

        document.location.href = location;
    }
}


