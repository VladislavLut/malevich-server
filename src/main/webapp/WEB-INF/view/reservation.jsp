<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">

  <link type="text/css" rel="stylesheet" href="<c:url value="/resources/static/css/materialize.css"/>" media="screen,projection" />

  <!-- <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection" /> -->
  <link href="<c:url value="/resources/static/css/footer.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/header.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/map.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/reservation.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/topbutton.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/entry.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/modal_dishcard.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/busket.css"/>" rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
    crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Oswald:200,300,400,500,600,700&amp;subset=cyrillic" rel="stylesheet">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
  <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
  <script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
  <script type="text/javascript" src="<c:url value="/resources/static/js/materialize.js"/>"></script>

  <title>Бронирование | MALEVICH ресторан</title>
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
        <div class="navBtn" onclick="showModalWindowOrProfile()" id="profileButton">
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

  <div class="wrapper">
    <div class="content">
      <div class="inputfields">
        <div class="input_container">
          <div class="caption">
            <p>Забронировать стол</p>
          </div>
          <div class="date_picker">
            <input type="date" name="Дата" id="date" placeholder="Выберите дату" value="" min="" onchange="changed_date()">
          </div>
          <div class="time_pick">
            <input type="text" class="timepicker" id="time" name="Время" placeholder="Выбрать время" onchange="changed_time()" readonly>
          </div>
          <div class="pretty-line">
            <hr>
          </div>
          <div class="select_free">
            <select class="browser-default" id="my_select" name="my_select" onchange="setRadioButtonFromSelect()">

            </select>
          </div>
          <div class="comments">
            <textarea name="Коментарии" id="comments" cols="20" rows="30" maxlength="150" placeholder="Можете оставить комментрий к бронированию"></textarea>
          </div>
          <div class="pretty-line">
            <hr>
          </div>
          <div class="phone_number">
            <input type="tel" name="Телефон" id="phone" maxlength="13" value="" placeholder="Номер телефона" onclick="showSample()">
          </div>
          <div class="enter_to_profile">
            <p>или
              <a onclick="PopUpShow()">Войти в профиль</a>
            </p>
          </div>
          <div class="sendReservation" onclick="test()">
            ЗАБРОНИРОВАТЬ
          </div>
        </div>
      </div>


      <div class="map">
        <div class="mapField">
          <img src="<c:url value="/resources/static/images/map.png"/>" alt="Map" width="832px" height="624px">

          <div class="radio_buttons">
            <div class="table1">
              <input class="rb" type="radio" name="option" id="radio1" value="1" onclick="setOptionToSelect()"/>
              <label for="radio1"><span class="x1">1</span></label>
            </div>
            <div class="table2">
              <input class="rb" type="radio" name="option" id="radio2" value="2" onclick="setOptionToSelect()"/>
              <label for="radio2"><span class="x">2</span></label>
            </div>
            <div class="table3">
              <input class="rb" type="radio" name="option" id="radio3" value="3" onclick="setOptionToSelect()"/>
              <label for="radio3"><span class="x">3</span></label>
            </div>
            <div class="table4">
              <input class="rb" type="radio" name="option" id="radio4" value="4" onclick="setOptionToSelect()"/>
              <label for="radio4"><span class="x">4</span></label>
            </div>
            <div class="table5">
              <input class="rb" type="radio" name="option" id="radio5" value="5" onclick="setOptionToSelect()"/>
              <label for="radio5"><span class="x">5</span></label>
            </div>
            <div class="table6">
              <input class="rb" type="radio" name="option" id="radio6" value="6" onclick="setOptionToSelect()"/>
              <label for="radio6"><span class="x">6</span></label>
            </div>
            <div class="table7">
              <input class="rb" type="radio" name="option" id="radio7" value="7" onclick="setOptionToSelect()"/>
              <label for="radio7"><span class="x1">7</span></label>
            </div>
            <div class="table8">
              <input class="rb" type="radio" name="option" id="radio8" value="8" onclick="setOptionToSelect()"/>
              <label for="radio8"><span class="x">8</span></label>
            </div>
            <div class="table9">
              <input class="rb" type="radio" name="option" id="radio9" value="9" onclick="setOptionToSelect()"/>
              <label for="radio9"><span class="x">9</span></label>
            </div>
            <div class="table10">
              <input class="rb" type="radio" name="option" id="radio10" value="10" onclick="setOptionToSelect()"/>
              <label for="radio10"><span class="xx">10</span></label>
            </div>
            <div class="table11">
              <input class="rb" type="radio" name="option" id="radio11" value="11" onclick="setOptionToSelect()"/>
              <label for="radio11"><span class="xx">11</span></label>
            </div>
            <div class="table12">
              <input class="rb" type="radio" name="option" id="radio12" value="12" onclick="setOptionToSelect()"/>
              <label for="radio12"><span class="xx">12</span></label>
            </div>
            <div class="table13">
              <input class="rb" type="radio" name="option" id="radio13" value="13" onclick="setOptionToSelect()"/>
              <label for="radio13"><span class="xx">13</span></label>
            </div>
            <div class="table14">
              <input class="rb" type="radio" name="option" id="radio14" value="14" onclick="setOptionToSelect()"/>
              <label for="radio14"><span class="xx">14</span></label>
            </div>
            <div class="table15">
              <input class="rb" type="radio" name="option" id="radio15" value="15" onclick="setOptionToSelect()"/>
              <label for="radio15"><span class="xx">15</span></label>
            </div>
            <div class="table16">
              <input class="rb" type="radio" name="option" id="radio16" value="16" onclick="setOptionToSelect()"/>
              <label for="radio16"><span class="xx">16</span></label>
            </div>
            <div class="table17">
              <input class="rb" type="radio" name="option" id="radio17" value="17" onclick="setOptionToSelect()"/>
              <label for="radio17"><span class="xx">17</span></label>
            </div>
            <div class="table18">
              <input class="rb" type="radio" name="option" id="radio18" value="18" onclick="setOptionToSelect()"/>
              <label for="radio18"><span class="xx">18</span></label>
            </div>
            <div class="table19">
              <input class="rb" type="radio" name="option" id="radio19" value="19" onclick="setOptionToSelect()"/>
              <label for="radio19"><span class="xx">19</span></label>
            </div>
            <div class="table20">
              <input class="rb" type="radio" name="option" id="radio20" value="20" onclick="setOptionToSelect()"/>
              <label for="radio20"><span class="xx">20</span></label>
            </div>
            <div class="table21">
              <input class="rb" type="radio" name="option" id="radio21" value="21" onclick="setOptionToSelect()"/>
              <label for="radio21"><span class="xx">21</span></label>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
  <a href="#" class="scrollup">
    <i class="fa fa-arrow-circle-up"></i>
  </a>

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
              <label for="entrylogin">Логин или телефон</label>
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
            <input class="new-input-user-data" id="birthdate" type="date" class="datepicker" name="birthdate">
            <label for="birthdate" class="" required="required" aria-required="true">Дата рождения</label>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12">
            <input class="new-input-user-data" id="regpassword" type="password" class="validate" name="regpassword">
            <label for="regpassword" required="required" aria-required="true">Пароль</label>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12">
            <input class="new-input-user-data" id="confirmpassword" type="password" class="validate" name="confirmpassword">
            <label for="confirmpassword" required="required" aria-required="true">Подтвердите пароль</label>
          </div>
        </div>
        <div class="entry-button" onclick="PopUp3Show(); PopUp2Hide(); clearRegForm();">ОТПРАВИТЬ</div>
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
    </p>s
  </div>
</div>

  <script src="<c:url value="/resources/static/js/date_picker.js"/>"></script>
  <script src="<c:url value="/resources/static/js/time_picker.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/static/js/topbutton.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/static/js/entryform.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/static/js/registrationform.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/static/js/busket.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/static/js/cart.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/static/js/reservation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/static/js/show_sapmle_for_reservation_field.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/static/js/authorization.js"/>"></script>

</body>

</html>