<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать!</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap&subset=cyrillic" rel="stylesheet">

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Montserrat', sans-serif;
            height: 100vh;
            width: 100vw;
            overflow: hidden;
            background: url("https://irecommend.ru/sites/default/files/imagecache/copyright1/user-images/694219/70dD7fgmge50X7q3YjpFCQ.JPG")
            center center / cover no-repeat fixed;
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
            color: #fff;
        }

        body::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: linear-gradient(to bottom right, rgba(80, 40, 20, 0.5), rgba(30, 15, 10, 0.8));
            z-index: 1;
        }
        .action-links {
            position: relative;
            z-index: 2;
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(10px);
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.5);
            text-align: center;
        }
        .action-links h2 {
            margin-top: 0;
            margin-bottom: 30px;
            font-size: 2em;
            color: #fff;
            text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.7);
        }
        .action-links a {
            display: inline-block;
            margin: 10px;
            padding: 15px 40px;
            background: rgba(139, 69, 19, 0.85);
            color: #fff;
            text-decoration: none;
            border-radius: 8px;
            border: 2px solid rgba(255, 255, 255, 0.2);
            font-weight: 700;
            font-size: 1.1em;
            transition: all 0.3s ease;
        }
        .action-links a:hover {
            background: rgba(160, 82, 45, 1);
            transform: translateY(-4px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.5);
        }
    </style>
</head>
<body>
<div class="action-links">
    <h2>Добро пожаловать в Планировщик задач!</h2>
    <a href="${pageContext.request.contextPath}/signUp">Зарегистрироваться</a>
    <a href="${pageContext.request.contextPath}/signIn">Войти</a>
</div>
</body>
</html>
