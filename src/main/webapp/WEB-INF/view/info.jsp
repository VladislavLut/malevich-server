<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<head>
  <link type="text/css" rel="stylesheet" href="<c:url value="/resources/static/css/materialize.min.css"/>" media="screen,projection" />

  <link href="<c:url value="/resources/static/css/header.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/info.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/footer.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/wrapper.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/topbutton.css"/>" rel="stylesheet">
  <link href="<c:url value="/resources/static/css/entry.css"/>" rel="stylesheet">

  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
    crossorigin="anonymous">
  <link href="https://fonts.googleapis.com/css?family=Oswald:200,300,400,500,600,700&amp;subset=cyrillic" rel="stylesheet">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
  <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
  <script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
  <script type="text/javascript" src="<c:url value="/resources/static/js/materialize.min.js"/>"></script>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>Доставка и оплата | MALEVICH ресторан</title>
  <link rel="shortcut icon" href="<c:url value="/resources/static/images/icon.ico"/>" type="image/x-icon">
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
        <div class="navBtn" onclick="PopUpShow()">
          <i class="fa fa-user fa-fw" aria-hidden="true"></i>
          <span>Вход</span>
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
              <a href="category.jsp">Пицца</a>
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
      <div class="terms-of-payment">
        <p class="title">Условия доставки</p>
        <p class="subtitle">Шедевры ресторана на дом или в офис</p>
        <p class="text">Служба доставки сети Служба доставки сети Малевич – это:
          <br>
          <br> • Бесплатная доставка при заказе от 100 грн в пригород
          <br> • Доставка пиццы: даже одну пиццу мы доставим бесплатно!
          <br> • Доставка во все районы Харькова, а также в пригород: Солоницевка, Песочин, авторынок «Лоск», Малая Даниловка
          <br> • Заказ по телефону удобного вам оператора мобильной связи, а также по городскому телефону!
          <br> • Он-лайн заказ на сайте
          <br> • Постоянные акции и скидки
          <br> • Скидки по дисконтным картам в сети ресторанов Малевич
          <br>
          <br> Технический перерыв с 05.00 до 10.00
          <br>
          <br> Интересует доставка еды на дом в Харькове - Малевич именно то место, где вам предложат большое количество соответствующих
          сервисов. Но только у нас, вы получите надежные и качественные услуги от профессионалов своего дела. Отличный способ
          питательно и вкусно пообедать на работе или перекусить дома, а может даже в гостях — это заказать доставку еды
          в Харькове от нашей сети ресторанов. Доставка еды от сети ресторанов Малевич осуществляется через несколько часов
          (но не более двух) после оформления вашего заказа. Услуга доступна круглосуточно все семь дней в неделю.</p>
      </div>
      <div class="other-terms">
        <div class="phones">
          <p class="subtitle">Телефоны</p>
          <p class="text">• (057) 752-03-30;
            <br>• (098) 337-39-39;
            <br>• (099) 449-39-39;
            <br>• (093) 831-39-39.</p>
        </div>
        <div class="giftcards">
          <p class="subtitle">Подарочные карты</p>
          <p class="text">Не знаете, что подарить?
            <br>
            <br> Подарите ужин в любимом итальянском ресторане! В продаже появились подарочные карты номиналом 100, 200, 300
            гривен, а также универсальная подарочная карта, которая может быть пополнена на любую сумму по вашему желанию.
            Каждая подарочная карта продается в комплекте с открыткой, на которой вы можете написать свое пожелание адресату.
            <br>
            <br> Подарочная карта может быть использована как платежная в ресторанах сети Малевич в Харькове.
            <br>
          </p>
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
  <script type="text/javascript" src="<c:url value="/resources/static/js/topbutton.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/static/js/entryform.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/static/js/registrationform.js"/>"></script>

</body>

<footer></footer>