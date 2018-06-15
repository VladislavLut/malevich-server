<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<head>
  <link type="text/css" rel="stylesheet" href="<c:url value="/resources/static/css/materialize.min.css"/>"
        media="screen,projection"/>


  <link href="<c:url value="/resources/static/css/wrapper.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/footer.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/profile.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/header.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/topbutton.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/fixed_header.css"/>" rel="stylesheet">

  <link href="<c:url value="/resources/static/css/busket.css"/>" rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
        crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Oswald:200,300,400,500,600,700&amp;subset=cyrillic" rel="stylesheet">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Профиль | MALEVICH ресторан</title>
  <link rel="shortcut icon" href="<c:url value="/resources/static/images/icon.ico"/>" type="image/x-icon">
  <script>
      $(document).ready(function () {
          //Скрыть PopUp при загрузке страницы
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
  </script>
</head>

<body>
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
          <span id="entryText">Профиль</span>
        </div>

      </a>
      <a class="temp" href="#" title="Отобразить карзину">
        <div class="navBtn">
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
    <div class="card">
      <div class="info">
        <div class="line">
          <p class="title">Логин</p>

        </div>
        <div class="line">
          <p class="title">Имя</p>

        </div>
        <div class="line">
          <p class="title">Телефон</p>

        </div>
        <div class="line">
          <p class="title">Дата рождения</p>

        </div>

      </div>

      <div class="info">
        <div class="line">
          <p class="data" id="profileLogin">Логин</p>

        </div>
        <div class="line">
          <p class="data" id="profileName">Имя</p>

        </div>
        <div class="line">
          <p class="data" id="profilePhone">Телефон</p>

        </div>
        <div class="line">
          <p class="data" id="profileDateOfBirth">Дата рождения</p>

        </div>
        <div class="button" onclick="logOut()">Выйти</div>
      </div>
    </div>
  </div>
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

<div class="b-popup" id="popup1">
  <div class="b-popup-content">
    <h3>Смена пароля</h3>
    <p>Введите старый пароль</p>
    <input class="password" type="password">
    <p>Новый пароль</p>
    <input class="password" type="password">
    <p>Повторите новый пароль</p>
    <input class="password" type="password">
    <div class="action-buttons">
      <a class="cancel-button" href="javascript:PopUpHide()">Отмена</a>
      <div class="button" onclick="">Отправить</div>
    </div>
  </div>
</div>
<a href="#" class="scrollup">
  <i class="fa fa-arrow-circle-up"></i>
</a>
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
<script type="text/javascript" src="<c:url value="/resources/static/js/topbutton.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/static/js/authorization.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/static/js/entryform.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/static/js/profile.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/static/js/menu.js"/>"></script>
<script>
    $(document).ready(function() {
        $('input#input_text, textarea#textarea2').characterCounter();
    });
</script>


</body>

<footer></footer>