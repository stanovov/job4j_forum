[![Build Status](https://app.travis-ci.com/stanovov/job4j_forum.svg?branch=master)](https://app.travis-ci.com/stanovov/job4j_forum)
[![alt-text](https://img.shields.io/badge/-heroku-283e4a?style=flat&logo=heroku&logoColor=white)](https://morning-reef-32109.herokuapp.com/)

![](https://img.shields.io/badge/Maven-=_3-red)
![](https://img.shields.io/badge/Java-=_14-orange)
![](https://img.shields.io/badge/Spring-=_5-darkorange)
![](https://img.shields.io/badge/Liquibase-=_3-f02a18)
![](https://img.shields.io/badge/PostgerSQL-=_9-blue)
![](https://img.shields.io/badge/JUnit-=_4-yellowgreen)
![](https://img.shields.io/badge/H2-0007c7)
![](https://img.shields.io/badge/Mockito-brightgreen)
![](https://img.shields.io/badge/Checkstyle-lightgrey)

# job4j_forum

+ [О проекте](#О-проекте)
+ [Технологии](#Технологии)
+ [Использование](#Использование)
+ [Контакты](#Контакты)

## О проекте

Веб-приложение реализует функционал закрытого форума. Есть возможность создавать темы, а также оставлять в них 
комментарии различным пользователям. Просматривать темы форума могут только зарегистрированные пользователи. Имеется 
функционал регистрации новых пользователей. В данном проекте используется фреймворк Spring. Приложение развернуто в 
облаке в сервисе [Heroku](https://morning-reef-32109.herokuapp.com/).

## Технологии

+ Сборщик проектов **Maven**;
+ Frontend - **HTML**, **CSS**, **BOOTSTRAP**;
+ Backend - **Java 14**, **Spring Boot**, **JSP**, **Liquibase**;
+ Тесты - **Junit**, **H2**, **Mockito**;
+ Облачная платформа - **Heroku**;
+ Логгирование - **Log4j**, **Slf4j**;
+ СУБД - **PostgreSQL**;
+ Непрерывная интеграция - **Travis CI**;
+ Инструмент для анализа стиля кода - **Checkstyle**;

## Использование

Первое, что видит любой неавторизованный пользователь, это окно авторизации:

![ScreenShot](images/login.png)

Для того чтобы пользоваться форумом, необходимо зарегистрироваться.

---

Перейдем на страницу регистрации:

![ScreenShot](images/reg_1.png)

Заполним необходимые поля и создадим нового пользователя:

![ScreenShot](images/reg_2.png)

---

После авторизации на форуме, мы попадём на основную страницу форума со всеми его темами:

![ScreenShot](images/main.png)

Сверху находится навигационное меню, где имеются кнопки: переход (обновление) на основную страницу форума, создание 
новой темы и выход из-под текущего пользователя. Мы можем просматривать любые темы и оставлять в них комментарии.

---

Создадим новую тему:

![ScreenShot](images/add_post.png)

После сохранения нас перебрасывает в неё:

![ScreenShot](images/new_post.png)

---

Вернёмся на основную страницу и убедимся, что новая тема присутствует на форуме, а также то, что она находится в самом 
верху:

![ScreenShot](images/new_post_main.png)

Также видно, что автор темы может отредактировать свою тему. Рядом с темой находится соответствующая кликабельная 
иконка.

---

Давайте перейдем во вторую тему и оставим какой-нибудь комментарий:

![ScreenShot](images/some_comment.png)

После отправки:

![ScreenShot](images/new_comment.png)

Также перейдем на главную страницу и увидим изменение в колонках "Количество ответов" и "Последний ответ":

![ScreenShot](images/new_comment_main.png)

## Контакты

Становов Семён Сергеевич

Email: sestanovov@gmail.com

Telegram: [@stanovovss](https://t.me/stanovovss)