$("document").ready(function() {
    const b = loadGoods();
});

var host = window.location.hostname.toString();

async function loadGoods() {
    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var myInit = { method: 'GET',
        headers: myHeaders,
        mode: 'same-origin',
        credentials: 'include' };

    var myRequest = new Request("/menu/category/" + location.search.toString().substring(1) + "/");
    try{
        await fetch(myRequest,myInit).then((response) => { return response.json()}).then((jsonObj) => {parseDishList(jsonObj); });

    }
    catch (e) {
        console.error(e);
    }
}

function sorting(jsonObj) {

    var select = document.getElementById("sorting");
    var value = select.value;

    if( value == "priceAsc") {
        return jsonObj.sort(function(obj1, obj2) {
            return obj1.price-obj2.price;
        })
    }
    if( value == "priceDec") {
        return jsonObj.sort(function(obj1, obj2) {
            return obj2.price-obj1.price;
        })
    }
    if( value == "lexic") {
        return jsonObj.sort(function(obj1, obj2) {
            if (obj1.name < obj2.name) return -1;
            if (obj1.name > obj2.name) return 1;
            return 0;
        })
    }
    else{
        return jsonObj;
    }
}

function parseDishList(jsonObj) {
    var sortJsonObj = sorting(jsonObj);
    console.log(jsonObj);

    var out = "";
    for (var key in sortJsonObj) {
        out +=
            '<div class="dish-card">' +
            '<img class="dish-img" src="' +
            sortJsonObj[key]["imageURL"] +
            '"onclick="PopUpDishCardShow()">';
        out += '<p class="dish-title" onclick="PopUpDishCardShow(this)">' + sortJsonObj[key]["name"] + "</p>";
        out +=
            '<div class="dish-description" onclick="PopUpDishCardShow(this)">' +
            sortJsonObj[key]["description"] +
            "</div>";
        out += '<p class="dish-cost">' + sortJsonObj[key]["price"] + " ₴</p>";
        out += '<div class="order-button" data-id="' + sortJsonObj[key]["id"] + '">Заказать</div></div>';
    }
    $("#goods").html(out);

    var d = document;
    var itemBox = d.querySelectorAll('.dish-card'); // блок каждого товара
    for(var i = 0; i < itemBox.length; i++){
        var button = itemBox[i].querySelector('.order-button');
        addEvent( button, 'click', addToCart);
    }
}

function removeQuantityOfDish(id) {
    var cartData = getCartData();
    if(cartData.dishes[id] > 1){
        cartData.dishes[id] = cartData.dishes[id] - 1;
    }
    setCartData(cartData);
    var idStr = "qunt_" + id;
    document.getElementById(idStr).innerHTML = cartData.dishes[id];
}

function addQuantityOfDish(id) {
    var cartData = getCartData();
    cartData.dishes[id] = cartData.dishes[id] + 1;
    setCartData(cartData);
    var idStr = "qunt_" + id;
    document.getElementById(idStr).innerHTML = cartData.dishes[id];
}

async function delete_from_basket(id) {
    var cartData = getCartData();
    cartData.dishes[id] = 1;
    try {
        var responseCart = await DeleteFromServerCart(cartData, id)
        //запрос добавить на сервер и в ответ получаем корзину
    }
    catch (e) {
        console.error(e);
    }
    if (!setCartData(responseCart)) { // Обновляем данные в LocalStorage
        showCart();
    }
}




function addEvent(elem, type, handler){
    if(elem.addEventListener){
        elem.addEventListener(type, handler, false);
    } else {
        elem.attachEvent('on'+type, function(){ handler.call( elem ); });
    }
    return false;
}



async function addToCart(){
    this.disabled = true; // блокируем кнопку на время операции с корзиной
    var cartData = getCartData() || {}, // получаем данные корзины или создаём новый объект, если данных еще нет"
        itemId = this.getAttribute('data-id'); // ID товара
    try {
        var responseCart = await AddIntoServerCart(cartData, itemId)
        //запрос добавить на сервер и в ответ получаем корзину
    }
    catch (e) {
        console.error(e);
    }

    if(!setCartData(responseCart)){ // Обновляем данные в LocalStorage
        this.disabled = false; // разблокируем кнопку после обновления LS

        showCart();
    }
}

function getCartData(){
    return JSON.parse(localStorage.getItem('cart'));
}


// Записываем данные в LocalStorage
function setCartData(o){
    localStorage.setItem('cart', JSON.stringify(o));
    return false;
}

// Добавляем товар в корзину


async function GetDishFromServer(arr) {
    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var myInit = { method: 'POST',
        headers: myHeaders,
        mode: 'same-origin',
        credentials: 'include',
        body: JSON.stringify(arr)};
    var myRequest = new Request("/menu/all/in");

    return fetch(myRequest,myInit).then((response) => { return response.json()});

}

async function AddIntoServerCart(cart, index) {
    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var myInit = { method: 'POST',
        headers: myHeaders,
        mode: 'same-origin',
        credentials: 'include',
        body: JSON.stringify(cart)};

    var myRequest = new Request("/cart/" + index + "/add");

    return fetch(myRequest,myInit).then((response) => { return response.json()});

}

async function DeleteFromServerCart(cart, index) {
    var myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    var myInit = { method: 'POST',
        headers: myHeaders,
        mode: 'same-origin',
        credentials: 'include',
        body: JSON.stringify(cart)};

    var myRequest = new Request("/cart/" + index + "/remove");

    return fetch(myRequest,myInit).then((response) => { return response.json()});

}

// Открываем корзину со списком добавленных товаров
async function showCart(){
    var cartData = getCartData() || {} // вытаскиваем все данные корзины

    // если что-то в корзине уже есть, начинаем формировать данные для вывода
    if(cartData !== null){
        var arrDish = [];
        for (var dish in cartData.dishes) {
            arrDish.push(dish);
        }
        try {
            var serverDishes =  await GetDishFromServer(arrDish);
            printCartDishes(serverDishes, cartData);
        }
        catch (e) {
            console.log(e)
        }
    }
}

async function printCartDishes(serverDish, cartData) {
    var totalItems = '';
    for (var i = 0; i < serverDish.length; i++) {
        totalItems +=
            '<div class="cart-dish" id="item_' + serverDish[i]["id"] + '">' +
            '<img src="' + serverDish[i]["imageURL"] +'">' +
            '<div class="cart-col">' +
            '<div class="delete-dish"><a onclick="delete_from_basket(' + serverDish[i]["id"] + ')"><i class="fas fa-times"></i></a></div>' +
            '<p class="cart-dish-title">' + serverDish[i]["name"] +'</p>' +
            '<div class="count-dish"><div class="count-dish-btn"><a onclick="removeQuantityOfDish(' + serverDish[i]["id"] + ')"><i class="far fa-minus-square"></i></a></div><p id="qunt_' + serverDish[i]["id"] + '">' + cartData.dishes[serverDish[i]["id"]]  + '</p><div class="count-dish-btn"><a onclick="addQuantityOfDish(' + serverDish[i]["id"] + ')"><i class="far fa-plus-square"></i></a></div></div>' +
            '<p class="cart-dish-cost">' + serverDish[i]["price"] + ' грн</p></div></div>';
    }
    document.getElementById("cart-list").innerHTML = totalItems;
}




