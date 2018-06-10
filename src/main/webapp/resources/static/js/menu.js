$("document").ready(function() {
    loadGoods();
});

var host = window.location.hostname.toString();
function loadGoods() {
    fetch("https://malevich-server.herokuapp.com/session/start", {credentials: "same-origin"}).then(() => console.warn("Cookie ok"));

    var requestMenuURL =  "https://malevich-server.herokuapp.com/menu/category/" + location.search.toString().substring(1) + "/";
    fetch(requestMenuURL, {credentials: 'include'})
        .then(function(response) {
            console.log(response.headers.get('Content-Type')); // application/json; charset=utf-8
            console.log(response.status);
            return response.json();
        })
        .then(function(jsonObj) {
            parseDishList(jsonObj);
        })
        .catch( console.log );
}

function parseDishList(jsonObj) {
    var out = "";
    for (var key in jsonObj) {
        out +=
            '<div class="dish-card">' +
            '<img class="dish-img" src="' +
            jsonObj[key]["imageURL"] +
            '"onclick="PopUpDishCardShow()">';
        out += '<p class="dish-title" onclick="PopUpDishCardShow()">' + jsonObj[key]["name"] + "</p>";
        out +=
            '<div class="dish-description" onclick="PopUpDishCardShow()">' +
            jsonObj[key]["description"] +
            "</div>";
        out += '<p class="dish-cost">' + jsonObj[key]["price"] + " ₴</p>";
        out += '<div class="order-button">Заказать</div></div>';
    }
    $("#goods").html(out);
}
/*
function setCookie(name, value, options) {
  options = options || {};

  var expires = options.expires;

  if (typeof expires == "number" && expires) {
    var d = new Date();
    d.setTime(d.getTime() + expires * 1000);
    expires = options.expires = d;
  }
  if (expires && expires.toUTCString) {
    options.expires = expires.toUTCString();
  }

  value = encodeURIComponent(value);

  var updatedCookie = name + "=" + value;

  for (var propName in options) {
    updatedCookie += "; " + propName;
    var propValue = options[propName];
    if (propValue !== true) {
      updatedCookie += "=" + propValue;
    }
  }

  document.cookie = updatedCookie;
}
*/

