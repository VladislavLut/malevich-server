<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

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
            <p class="data">Логин</p>

          </div>
          <div class="line">
            <p class="data">Имя</p>

          </div>
          <div class="line">
            <p class="data">Телефон</p>

          </div>
          <div class="line">
            <p class="data">Дата рождения</p>

          </div>
          <div class="button" onclick="PopUpShow()">Изменить пароль</div>
        </div>
      </div>
    </div>
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



</body>

<footer></footer>