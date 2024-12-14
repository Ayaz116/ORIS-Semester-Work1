<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Ошибка</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #ffefef;
            color: #b71c1c;
        }
        .error-container {
            margin-top: 20%;
        }
        .error-container h1 {
            font-size: 3em;
            margin-bottom: 10px;
        }
        .error-container p {
            font-size: 1.5em;
            color: #666;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>Ooops, что-то пошло не так...</h1>
    <p><c:out value="${param.err}" /></p>
</div>
</body>
</html>
