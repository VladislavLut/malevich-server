$(document).ready(function() {
    PopUp2Hide();
    PopUp3Hide();
    $("#form").validate({
        errorClass: "invalid",
        errorPlacement: function(error, element) {
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']")
                .attr("data-error", error.text());
        },
        rules: {
            reglogin: {
                required: true,
                minlength: 5,
                maxlength: 20,
                pattern: /^[а-яА-ЯёЁa-zA-Z0-9]{5,20}$/
            },
            regname: {
                required: true,
                minlength: 3,
                maxlength: 25,
                pattern: /^[а-яА-ЯёЁ ]{2,25}$/
            },
            regtel: {
                required: true,
                minlength: 13,
                pattern: /^(\+380)([0-9]){9}$/
            },
            birthdate: {
                required: true
            },
            regpassword: {
                required: true,
                minlength: 6,
                maxlength: 20,
                pattern: /^[а-яА-ЯёЁa-zA-Z0-9_!@#$%^&*]{6,20}$/,
            },
            confirmpassword: {
                required: true,
                equalTo: "#regpassword"
            }
        },
        //For custom messages
        messages: {
            reglogin: {
                required: "Enter a username",
                minlength: "Enter at least 5 characters"
            },
            regtel: "Enter your website"
        }
    });
});

function PopUp2Show() {
    $("#popup2").show();
}

function PopUp2Hide() {
    $("#popup2").hide();
}
function clearRegForm(){
    $("#form")[0].reset();
}

function PopUp3Show() {
    $("#popup3").show();
}

function PopUp3Hide() {

    $("#popup3").hide();
}

function registrate(){
    if (checkFields()) {
        var user = {};
        user.type = "CLIENT";
        user.login = document.getElementById("reglogin").value;
        user.password = document.getElementById("regpassword").value;
        user.name = document.getElementById("regname").value;
        user.birthDay = document.getElementById("birthdate").value;
        user.phone = document.getElementById("regtel").value;
        fetch("/session/start",{credentials: "same-origin"}).then(() => addUser(user));
        PopUp3Show();
        PopUp2Hide();
        clearRegForm();
    }else{
        alert("Не все поля были заполнены верно, попробуйте еще раз.");
    }
}

function addUser(user) {
    fetch("/users/add", {
        credentials: "include",
        method: "POST",
        headers: {
            Accept: "application/json;",
            "Content-Type": "application/json;"
        },
        body: JSON.stringify(user)
    }).then(res => res).then((response) => { console.warn(response); if(response.status == 500){alert("Пользователь с таким логином уже существует!")}});
}

function checkFields() {
    var isLogin = /^[а-яА-ЯёЁa-zA-Z0-9]{5,20}$/.test(document.getElementById("reglogin").value);
    var isName = /^[а-яА-ЯёЁ ]{2,25}$/.test(document.getElementById("regname").value);
    var isTel = /^(\+380)([0-9]){9}$/.test(document.getElementById("regtel").value);

    var minDate = new Date('10/10/1900');
    var maxDate = new Date('06/06/2018');
    var currDate = Date.parse(document.getElementById("birthdate").value);
    var isPassword = /^[а-яА-ЯёЁa-zA-Z0-9_!@#$%^&*]{6,20}$/.test(document.getElementById("regpassword").value) &&
        document.getElementById("regpassword").value == document.getElementById("confirmpassword").value;
    var isBirthDay = (currDate > minDate && currDate < maxDate);
    return isLogin && isName && isTel && isPassword && isBirthDay;
}
