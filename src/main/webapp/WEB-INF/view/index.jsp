<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/static/css/materialize.min.css"/>"
          media="screen,projection"/>

    <link href="<c:url value="/resources/static/css/wrapper.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/static/css/footer.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/static/css/about.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/static/css/header.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/static/css/topbutton.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/static/css/fixed_header.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/static/css/entry.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/static/css/modal_dishcard.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/static/css/busket.css"/>" rel="stylesheet">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Oswald:200,300,400,500,600,700&amp;subset=cyrillic"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
    <script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/static/js/materialize.min.js"/>"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>О ресторане | MALEVICH ресторан</title>
    <link rel="shortcut icon" href="<c:url value="/resources/static/images/icon.ico"/>" type="image/x-icon">

</head>

<body>
<!--Корзина-->
<div class="cart">
    <div class="cart-head">
        <div class="icon-close">
            <a href="#">
                <i class="fas fa-chevron-circle-right"></i>
            </a>
        </div>
        <div class="cart-title">Заказ</div>
    </div>
    <div class="cart-list">

    </div>
    <div class="cart-footer">
        <div class="cart-sum">
            <p>Сумма:</p>
            <p>0</p>
        </div>
        <div class="cart-button">
            Оформить
        </div>
    </div>
</div>
<!--Корзина-->
<header>
    <div class="firstRow">
        <div class="socIcon">
            <a href="https://www.instagram.com/">
                <i class="fab fa-instagram fa-2x" aria-hidden="true" style="color: white;"></i>
            </a>
            <a href="https://www.facebook.com/">
                <i class="fab fa-facebook-square fa-2x" aria-hidden="true" style="color:white ;"></i>
            </a>
            <a href="https://twitter.com/">
                <i class="fab fa-twitter-square fa-2x" aria-hidden="true" style="color: white;"></i>
            </a>
            <a href="https://plus.google.com/discover">
                <i class="fab fa-google-plus-square fa-2x" aria-hidden="true" style="color: white;"></i>
            </a>
        </div>

        <div class="logo">
            <a href="<c:url value="/"/>" title="На главную страничку">
                <span>
            <span style="color: #ea2727">M</span>ALEVICH</span>
            </a>
        </div>

        <div class="ProfileAndBasket">
            <a class="temp" href="#" title="Войти в кабинет пользователя">
                <div class="navBtn" onclick="showModalWindowOrProfile()" id="profileButton">
                    <i class="fa fa-user fa-fw" aria-hidden="true"></i>
                    <span id="entryText">Вход</span>
                </div>
            </a>
            <a class="temp" href="#" title="Отобразить карзину">
                <div class="navBtn busket-btn">
                    <i class="fa fa-shopping-basket fa-fw" aria-hidden="true"></i>
                    <span>Корзина</span>
                </div>
            </a>
        </div>
    </div>

    <div class="secondRow" style="z-index: 100;">
        <div class="nav-menu">
            <ul class="menu">
                <li class="menu__list">
                    <a href="<c:url value="menu"/>">МЕНЮ
                        <i class="down"></i>
                    </a>
                    <ul>
                        <li>
                            <a href="<c:url value="category"/>?pizza">Пицца</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?burger">Бургеры</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?sushi">Суши</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?snack">Закуски</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?salad">Салаты</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?pasta">Паста</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?dessert">Десерты</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?drinks">Напитки</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="<c:url value="info"/>">ДОСТАВКА И ОПЛАТА</a>
                </li>
                <li>
                    <a href="<c:url value="reservation"/>">ОНЛАЙН БРОНЬ</a>
                </li>
                <li class="menu__list">
                    <a href="<c:url value="/"/>">О НАС
                        <i class="down"></i>
                    </a>
                    <ul class="dropdown-menu_about">
                        <li>
                            <a href="<c:url value="/"/>#kitchen">Кухня</a>
                        </li>
                        <li>
                            <a href="<c:url value="/"/>#staff">Шеф-повары</a>
                        </li>
                        <li>
                            <a href="<c:url value="/"/>#restaurant">Рестораны</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</header>

<div class="fixed" style="z-index: 100;">
    <div class="fixed-block">
        <div class="fixed-menu-logo">
            <a href="<c:url value="/"/>" title="На главную страничку">
          <span>
            <span style="color: red">M</span>ALEVICH</span>
            </a>
        </div>
        <div class="fixed-menu">
            <ul>
                <li class="menu__list">
                    <a href="<c:url value="menu"/>">МЕНЮ
                        <i class="down"></i>
                    </a>
                    <ul>
                        <li>
                            <a href="<c:url value="category"/>?pizza">Пицца</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?burger">Бургеры</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?sushi">Суши</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?snack">Закуски</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?salad">Салаты</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?pasta">Паста</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?dessert">Десерты</a>
                        </li>
                        <li>
                            <a href="<c:url value="category"/>?drinks">Напитки</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="<c:url value="info"/>">ДОСТАВКА И ОПЛАТА</a>
                </li>
                <li>
                    <a href="<c:url value="reservation"/>">ОНЛАЙН БРОНЬ</a>
                </li>
                <li class="menu__list">
                    <a href="<c:url value="/"/>">О НАС
                        <i class="down"></i>
                    </a>
                    <ul>
                        <li>
                            <a href="<c:url value="/"/>#kitchen">Кухня</a>
                        </li>
                        <li>
                            <a href="<c:url value="/"/>#staff">Шеф-повары</a>
                        </li>
                        <li>
                            <a href="<c:url value="/"/>#restaurant">Рестораны</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="fixed-basket">
            <div class="fixed-navBtn busket-btn">
                <i class="fa fa-shopping-basket fa-fw" aria-hidden="true"></i>
                <span>Корзина</span>
            </div>
        </div>
    </div>
</div>

<div class="wrapper" style="z-index: 95;">
    <div>
        <div class="slider">
            <ul class="slides">
                <li>
                    <img src="<c:url value="/resources/static/images/slider1.jpg"/>">
                    <!-- random image -->
                    <div class="caption center-align">
                        <h3>Проведи вечер с комфортом</h3>
                        <h5 class="light grey-text text-lighten-3">Теперь забронировать столик еще проще</h5>
                    </div>
                </li>
                <li>
                    <img src="<c:url value="/resources/static/images/slider2.jpg"/>">
                    <!-- random image -->
                    <div class="caption left-align">
                        <h3>Счастливые часы</h3>
                        <h5 class="light grey-text text-lighten-3">Скидка 20% с 12.00 до 16.00</h5>
                    </div>
                </li>
                <li>
                    <img src="<c:url value="/resources/static/images/slider3.jpg"/>">
                    <!-- random image -->
                    <div class="caption right-align">
                        <h3>Новые позиции</h3>
                        <h5 class="light grey-text text-lighten-3">Обновлено меню азиатской кухни</h5>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    <div class="content" style="z-index: 90;">
        <a name="kitchen"></a>
        <div class="section-block">
            <div class="section-title">
                <p class="section-title-label">
                    <span class="letter">К</span>УХНЯ</p>

            </div>
            <div class="section-description">
                <p class="section-text">В меню - блюда итальянской кухни в авторском исполнении от нашего шеф-повара.
                    Гостям здесь предложат Филе сибаса
                    с лимонным соусом, Паштет из печени цесарки в вишневой глазури, Генуэзский мясной пирог с винным
                    соусом и прочие
                    шедевры авторской кухни.</p>
            </div>
        </div>

        <div class="dish-items">
            <div class="dish-item">
                <img class="dish-image" src="<c:url value="/resources/static/images/dish1.jpg"/>" alt="Малевич">
            </div>
            <div class="dish-item">
                <img class="dish-image" src="<c:url value="/resources/static/images/dish2.jpg"/>" alt="Малевич">
            </div>
            <div class="dish-item">
                <img class="dish-image" src="<c:url value="/resources/static/images/dish3.jpg"/>" alt="Малевич">
            </div>
            <div class="dish-item">
                <img class="dish-image" src="<c:url value="/resources/static/images/dish4.jpg"/>" alt="Малевич">
            </div>
        </div>
        <a name="staff"></a>
        <div class="section-block">
            <div class="section-title">
                <p class="section-title-label">
                    <span class="letter">П</span>ЕРСОНАЛ</p>
            </div>
            <div class="section-description">
                <p class="section-text">Я родился в Неаполе. Работал в разных регионах Италии, позже в Британии и
                    Германии. В 2011 году перехал в Харьков,
                    а с 2012 года являюсь шеф-поваром ресторана "Малевич". В каждом своем блюде стараюсь передать особую
                    атмосферу
                    местности, из которой оно родом. Именно поэтому находясь в Украине у вас есть уникальная возможность
                    не на долго
                    оказаться в Италии</p>
            </div>
        </div>

        <div class="kitcheners">
            <div class="content-kitchener-block">
                <img class="kitchener-image" src="<c:url value="/resources/static/images/kitchener1.jpg"/>"
                     alt="Малевич">
                <div class="kitchener-description">
                    <p class="kitchener-citation">"В Италии я работал во многих ресторанах, даже награждённых
                        мишленовскими звёздами, потом перебрался в Украину,
                        с 2014 работаю су-шефом в ресторане "Малевич"</p>
                </div>
            </div>
            <div class="content-kitchener-block">
                <img class="kitchener-image" src="<c:url value="/resources/static/images/kitchener2.jpg"/>"
                     alt="Малевич">
                <div class="kitchener-description">
                    <p class="kitchener-citation">"Всю жизнь я работал в Италии, впервые оказался за её пределами, когда
                        перебрался в Харьков. Рад тому, что своими
                        блюдами передаю кусочек родины украинцам"</p>
                </div>
            </div>
            <div class="content-kitchener-block">
                <img class="kitchener-image" src="<c:url value="/resources/static/images/kitchener3.jpg"/>"
                     alt="Малевич">
                <div class="kitchener-description">
                    <p class="kitchener-citation">"Я — потомственный пиццайоло, ради этой профессии когда-то бросил
                        школу. Жизнь в Харькове мне нравится, в "Малевиче"
                        работаю с 2011 года."</p>
                </div>
            </div>
        </div>
        <a name="restaurant"></a>
        <div class="section-block">
            <div class="section-title">
                <p class="section-title-label">
                    <span class="letter">Р</span>ЕСТОРАН</p>
            </div>
            <div class="section-description">
                <p class="section-text">"Малевич" - ресторан изысканной итальянской кухни в самом центре Харькова. Вы
                    спрсите, почему именно Малевич? Ведь
                    сам Казимир Северинович родом из Санкт-Петербуга, и в Италии никогда не был... Не будем открывать
                    все карты сразу,
                    приходите и узнаете...</p>
            </div>
        </div>

        <iframe class="map"
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2563.8314807172615!2d36.22548581511383!3d50.01451372656166!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4127a1243f9e69d1%3A0x6daed05394f03251!2z0KXQsNGA0LrRltCy0YHRjNC60LjQuSDQvdCw0YbRltC-0L3QsNC70YzQvdC40Lkg0YPQvdGW0LLQtdGA0YHQuNGC0LXRgiDRgNCw0LTRltC-0LXQu9C10LrRgtGA0L7QvdGW0LrQuCAo0KXQndCj0KDQlSwgTlVSRSk!5e0!3m2!1sru!2sua!4v1527345921823">
        </iframe>
        <a href="#" class="scrollup">
            <i class="fa fa-arrow-circle-up"></i>
        </a>
    </div>

    <div class="b-popup" id="popup1">
        <div class="b-popup-content">
            <p>
                <a class="close-button" href="#" onclick="PopUpHide()">
                    <i class="fa fa-window-close" aria-hidden="true"></i>
                </a>
            </p>
            <h3 class="entry-title">Добро пожаловать в MALEVICH</h3>
            <div class="entry-col">
                <div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input class="input-user-data" id="entrylogin" type="email" class="validate">
                            <label for="entrylogin">Логин</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input class="input-user-data" id="entry-password" type="password" class="validate">
                            <label for="entry-password">Пароль</label>
                        </div>
                    </div>
                    <div class="entry-button" onclick="LogIn()">ВОЙТИ</div>
                </div>
                <div>
                    <p class="entry-label">Впервые на сайте?</p>
                    <div class="entry-button" onclick="PopUp2Show(); PopUpHide();">ЗАРЕГЕСТРИРОВАТЬСЯ</div>
                    <p class="entry-label-desc">После регистрации заказ и брониование - на 25% быстрее</p>
                </div>
            </div>
        </div>
    </div>
    <div class="b-popup" id="popup2">
        <div class="b-popup-content2">
            <p>
                <a class="close-button" href="#" onclick="PopUp2Hide()">
                    <i class="fa fa-window-close" aria-hidden="true"></i>
                </a>
            </p>
            <form id="form">
                <h3 class="entry-title">Регистрация</h3>
                <div class="row">
                    <div class="input-field col s12">
                        <input class="new-input-user-data" id="reglogin" type="text" class="validate" name="reglogin">
                        <label for="reglogin" required="required" aria-required="true">Логин</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input class="new-input-user-data" id="regname" type="text" class="validate" name="regname">
                        <label for="regname" required="required" aria-required="true">Имя</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input class="new-user-data" id="regtel" type="tel" class="validate" name="regtel">
                        <label for="regtel" required="required" aria-required="true">Номер телефона</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input class="new-input-user-data" id="birthdate" type="date" class="datepicker" min="1900-10-10" max="2018-06-06"
                               name="birthdate">
                        <label for="birthdate" class="" required="required" aria-required="true">Дата рождения</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input class="new-input-user-data" id="regpassword" type="password" class="validate"
                               name="regpassword">
                        <label for="regpassword" required="required" aria-required="true">Пароль</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input class="new-input-user-data" id="confirmpassword" type="password" class="validate"
                               name="confirmpassword">
                        <label for="confirmpassword" required="required" aria-required="true">Подтвердите пароль</label>
                    </div>
                </div>
                <div class="entry-button" onclick="registrate()">ОТПРАВИТЬ</div>
            </form>
        </div>
    </div>

    <div class="b-popup" id="popup3">
        <div class="b-popup-content3">
            <p>
                <a class="close-button" href="#" onclick="PopUp3Hide()">
                    <i class="fa fa-window-close" aria-hidden="true"></i>
                </a>
            </p>
            <h3 class="entry-title">Спасибо за регистрацию</h3>
            <p class="entry-label">Подтверждение будет отправлено на указанный телефон в ближайшее время</p>
        </div>
    </div>

    <div class="b-popup" id="popup-order">
        <div class="b-popup-content-order">
            <p>
                <a class="close-button" href="#" onclick="PopUpOrderHide()">
                    <i class="fas fa-times"></i>
                </a>
            </p>
            <p id="OrderTitle">Оформление заказа</p>

            <form id="form-order">
                <div class="row">
                    <div id = "numOr" class="input-field col s12">
                        <input class="new-user-data" id="telOrder" type="tel" class="validate" name="regtel">
                        <label for="regtel" required="required" aria-required="true">Номер телефона</label>
                    </div>
                </div>
                <div class="row">
                    <div id = "ComOr" class="input-field col s12">
                        <textarea id="textarea2" class="materialize-textarea" data-length="256"></textarea>
                        <label for="textarea2">Ваш комментарий к заказу</label>
                    </div>
                </div>

            </form>

            <p class="orderP">Сумма заказа: <span class="orderP" id="order-sum">0</span> грн</p>
            <p class="orderP">Доставка: <span class="orderP" id="order-del">50</span> грн</p>
            <p class="orderP">Всего: <span class="orderP" id="order-amount">0</span> грн</p>
            <div class="send-order-button" onclick="SendOrder();PopUpOrderHide()">ОТПРАВИТЬ</div>
        </div>
    </div>

    <div class="footer">
        <img class="footer-logo" src="<c:url value="/resources/static/images/logo.png"/>" alt="Малевич">
        <div>
            <p class="footer-company">© 2002 - 2018 MALEVICH
                <br>Все права защищены.
                <br>
                <span class="footer-devlabel">Разработка сайта IMAGINARY TURTLES</span>
            </p>
        </div>

        <div>
            <p class="footer-links">
                <a href="<c:url value="menu"/>">Меню</a>
            </p>
            <p class="footer-links">
                <a href="<c:url value="/"/>">Профиль</a>
            </p>
        </div>
        <div>
            <p class="footer-links">
                <a href="<c:url value="info"/>">Доставка и оплата</a>
            </p>
            <p class="footer-links">
                <a href="<c:url value="reservation"/>">Онлайн бронь</a>
            </p>
        </div>
        <div>
            <p class="footer-links">
                <a href="<c:url value="/"/>">О нас</a>
            </p>
            <p class="footer-links">
                <a href="#"> </a>
            </p>
        </div>
        <div class="footer-social">
            <p class="footer-icons">
                <a href="https://www.instagram.com/?hl=ru">
                    <i class="fab fa-instagram"></i>
                </a>
            </p>
            <p class="footer-icons">
                <a href="https://www.facebook.com/">
                    <i class="fab fa-facebook-square"></i>
                </a>
            </p>
            <p class="footer-icons">
                <a href="https://www.facebook.com/">
                    <i class="fab fa-twitter-square"></i>
                </a>
            </p>
            <p class="footer-icons">
                <a href="https://www.facebook.com/">
                    <i class="fab fa-google-plus-square"></i>
                </a>
            </p>
        </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function () {
            $('.slider').slider({
                height: 480
            });
        });
    </script>
    <script type="text/javascript" src="<c:url value="/resources/static/js/authorization.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/static/js/topbutton.js"/>"></script>
    <script src="<c:url value="/resources/static/js/fixed_header.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/static/js/entryform.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/static/js/registrationform.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/static/js/busket.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/static/js/cart.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/static/js/menu.js"/>"></script>
    <script>
        $(document).ready(function() {
            $('input#input_text, textarea#textarea2').characterCounter();
        });
    </script>

</body>

<footer></footer>
