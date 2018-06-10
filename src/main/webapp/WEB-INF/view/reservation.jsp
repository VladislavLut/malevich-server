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
  <script type="text/javascript" src="<c:url value="/resources/static/js/materialize.min.js"/>"></script>

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
        <div class="navBtn" onclick="PopUpShow()">
          <i class="fa fa-user fa-fw" aria-hidden="true"></i>
          <span>Вход</span>
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
              <a href="<c:url value="category"/>">Пицца</a>
            </li>
            <li>
              <a href="#">Бургеры</a>
            </li>
            <li>
              <a href="#">Суши</a>
            </li>
            <li>
              <a href="#">Закуски</a>
            </li>
            <li>
              <a href="#">Салаты</a>
            </li>
            <li>
              <a href="#">Паста</a>
            </li>
            <li>
              <a href="#">Десерты</a>
            </li>
            <li>
              <a href="#">Напитки</a>
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
            <input type="date" name="Дата" id="date" placeholder="Выберите дату" value="">
          </div>
          <div class="time_pick">
            <input type="text" class="timepicker" id="time" name="Время" placeholder="Выбрать время" readonly>
          </div>
          <div class="pretty-line">
            <hr>
          </div>
          <div class="select_free">
            <select class="browser-default">
              <option value="" disabled selected>Выбрать столик</option>
              <option value="1">1 столик</option>
              <option value="2">2 столик</option>
              <option value="3">3 столик</option>
              <option value="4">4 столик</option>
            </select>
          </div>
          <div class="comments">
            <textarea name="Коментарии" id="comments" cols="20" rows="30" maxlength="150" placeholder="Можете оставить комментрий к бронированию"></textarea>
          </div>
          <div class="pretty-line">
            <hr>
          </div>
          <div class="phone_number">
            <input type="tel" name="Телефон" id="phone" maxlength="13" value="+380">
          </div>
        </div>
      </div>


      <div class="map">
        <div class="mapField">
          <img src="<c:url value="/resources/static/images/map.png"/>" alt="Map" width="832px" height="624px">

          <div class="radio_buttons">
            <div class="table1">
              <input class="rb" type="radio" name="option" id="radio1" />
              <label for="radio1">1</label>
            </div>
            <div class="table2">
              <input class="rb" type="radio" name="option" id="radio2" />
              <label for="radio2">2</label>
            </div>
            <div class="table3">
              <input class="rb" type="radio" name="option" id="radio3" />
              <label for="radio3">3</label>
            </div>
            <div class="table4">
              <input class="rb" type="radio" name="option" id="radio4" />
              <label for="radio4">4</label>
            </div>
            <div class="table5">
              <input class="rb" type="radio" name="option" id="radio5" />
              <label for="radio5">5</label>
            </div>
            <div class="table6">
              <input class="rb" type="radio" name="option" id="radio6" />
              <label for="radio6">6</label>
            </div>
            <div class="table7">
              <input class="rb" type="radio" name="option" id="radio7" />
              <label for="radio7">7</label>
            </div>
            <div class="table8">
              <input class="rb" type="radio" name="option" id="radio8" />
              <label for="radio8">8</label>
            </div>
            <div class="table9">
              <input class="rb" type="radio" name="option" id="radio9" />
              <label for="radio9">9</label>
            </div>
            <div class="table10">
              <input class="rb" type="radio" name="option" id="radio10" />
              <label for="radio10">10</label>
            </div>
            <div class="table11">
              <input class="rb" type="radio" name="option" id="radio11" />
              <label for="radio11">11</label>
            </div>
            <div class="table12">
              <input class="rb" type="radio" name="option" id="radio12" />
              <label for="radio12">12</label>
            </div>
            <div class="table13">
              <input class="rb" type="radio" name="option" id="radio13" />
              <label for="radio13">13</label>
            </div>
            <div class="table14">
              <input class="rb" type="radio" name="option" id="radio14" />
              <label for="radio14">14</label>
            </div>
            <div class="table15">
              <input class="rb" type="radio" name="option" id="radio15" />
              <label for="radio15">15</label>
            </div>
            <div class="table16">
              <input class="rb" type="radio" name="option" id="radio16" />
              <label for="radio16">16</label>
            </div>
            <div class="table17">
              <input class="rb" type="radio" name="option" id="radio17" />
              <label for="radio17">17</label>
            </div>
            <div class="table18">
              <input class="rb" type="radio" name="option" id="radio18" />
              <label for="radio18">18</label>
            </div>
            <div class="table19">
              <input class="rb" type="radio" name="option" id="radio19" />
              <label for="radio19">19</label>
            </div>
            <div class="table20">
              <input class="rb" type="radio" name="option" id="radio20" />
              <label for="radio20">20</label>
            </div>
            <div class="table21">
              <input class="rb" type="radio" name="option" id="radio21" />
              <label for="radio21">21</label>
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
          <div class="entry-button">ВОЙТИ</div>
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
    </p>
  </div>
</div>

  <script src="<c:url value="/resources/static/js/date_picker.js"/>"></script>
  <script src="<c:url value="/resources/static/js/time_picker.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/static/js/topbutton.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/static/js/entryform.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/static/js/registrationform.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/static/js/busket.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/static/js/cart.js"/>"></script>
</body>

</html>