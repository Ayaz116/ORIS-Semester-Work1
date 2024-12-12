<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Вход</title>
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap&subset=cyrillic" rel="stylesheet">
  <style>
    body {
      margin:0; padding:0; font-family:'Montserrat', sans-serif;
      height:100vh; width:100vw; overflow:hidden;
      background: url("https://irecommend.ru/sites/default/files/imagecache/copyright1/user-images/694219/70dD7fgmge50X7q3YjpFCQ.JPG")
      center center / cover no-repeat fixed;
      display:flex; justify-content:center; align-items:center;
      position:relative; color:#fff;
    }
    body::before {
      content:"";
      position:absolute; top:0; left:0; right:0; bottom:0;
      background: linear-gradient(to bottom right, rgba(80,40,20,0.5), rgba(30,15,10,0.8));
      z-index:1;
    }
    .form-container {
      position:relative; z-index:2;
      background:rgba(255,255,255,0.15);
      backdrop-filter:blur(10px);
      padding:40px;
      border-radius:12px;
      box-shadow:0 8px 30px rgba(0,0,0,0.5);
      text-align:center;
      width:360px;
    }
    .form-container h2 {
      margin-top:0; margin-bottom:30px;
      font-size:1.8em; color:#fff; text-shadow:2px 2px 5px rgba(0,0,0,0.7);
    }
    .errorMessage {
      color:#b3b3b3; font-size:0.9em; font-weight:normal;
      background-color:rgba(255,255,255,0.2);
      padding:8px; border-radius:6px; margin-bottom:15px;
    }
    .form-container label {
      display:block; text-align:left; font-weight:bold; margin:15px 0 5px; color:#fff;
      text-shadow:1px 1px 3px rgba(0,0,0,0.5);
    }
    .form-container input {
      width:100%; padding:10px; border:none; border-radius:5px;
      margin-bottom:10px; font-size:1em; box-sizing:border-box;
    }
    .form-container input[type="submit"] {
      background:rgba(139,69,19,0.85);
      color:#fff; border-radius:8px;
      border:2px solid rgba(255,255,255,0.2);
      font-weight:700; font-size:1.1em; padding:10px; cursor:pointer;
      transition: all 0.3s ease; margin-top:20px;
    }
    .form-container input[type="submit"]:hover {
      background:rgba(160,82,45,1);
      transform: translateY(-4px);
      box-shadow:0 5px 15px rgba(0,0,0,0.5);
    }
    .form-container a {
      display:block; margin-top:20px; color:#fff; text-decoration:none; font-weight:bold;
      text-shadow:1px 1px 3px rgba(0,0,0,0.5); transition:color 0.3s;
    }
    .form-container a:hover {
      color:#ccc;
    }
  </style>
</head>
<body>
<div class="form-container">
  <h2>Вход</h2>
  <% if (request.getAttribute("errorMessage") != null) { %>
  <div class="errorMessage">
    <%= request.getAttribute("errorMessage") %>
  </div>
  <% } %>
  <form action="/signIn" method="post">
    <label for="email">Email:</label>
    <input id="email" name="email" type="email" placeholder="email@main.ru" required/>

    <label for="password">Пароль:</label>
    <input id="password" name="password" type="password" required/>

    <input type="submit" value="Войти"/>
  </form>
  <a href="/signUp">Нет аккаунта? Зарегистрируйтесь</a>
  <a href="/">Назад на главную</a>
</div>
</body>
</html>
