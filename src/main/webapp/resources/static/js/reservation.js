// $("document").ready(function () {
//     LogIn();
// });
//
//
// /* НИКИТА  */
//
// async function LogIn() {
//     var nowDate = document.getElementById("date").value;
//     // var password = document.getElementById("entry-password").value;
//     try {
//         await startSession();
//         const response = await getUser();
//         const user = await response.json();
//         await showReservedTable(nowDate);
//     } catch(error){
//         console.error(error);
//     }
//     // if(isLogedIn()) {
//     //     PopUpHide();
//     //     document.getElementById("entrylogin").value = "";
//     //     document.getElementById("entry-password").value = "";
//     // }
//     // else {
//     //     document.getElementById("entrylogin").value = "";
//     //     document.getElementById("entry-password").value = "";
//     //     alert("Неверный пароль или логин, попробуйте еще раз!")
//     // };
//
// }
//
// function startSession() {
//     return fetch("https://malevich-test.herokuapp.com/session/start", {credentials: "same-origin"});
// }
//
// function getUser(login, password) {
//     return fetch('/login', {method: "POST", headers: {
//             Accept: "application/json",
//             "Content-Type": "application/json"
//         },
//         body: JSON.stringify({login:"ihor", password:"11111111"})}
//     );
// }
//
// function isLogedIn() {
//     if (JSON.parse(localStorage.getItem("user")).error)
//         return false;
//     else return true;
// }

function showReservedTable(nowDate) {
    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var myInit = { method: 'GET',
        headers: myHeaders,
        mode: 'same-origin',
        credentials: 'include' };

    var myRequest = new Request('/reserved/all');
    // return fetch('/reserved/all', {credentials: "same-origin"})
    //     .then((response) => { return response.text();}).then((user) => {console.log(user);});
    return fetch(myRequest,myInit).then((response) => { return response.text();}).then((user) => {console.log(user);});
}

