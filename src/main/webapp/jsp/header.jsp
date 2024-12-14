<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title><c:out value="${pageTitle}" /></title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="wrapper">
    <header class="top-header">
        <nav class="top-nav">
            <c:if test="${not empty userName}">
                <span class="welcome-message">Добро пожаловать, <b>${userName}</b>!</span>
                <a href="/logout">Выйти</a>
                <a href="/dashboard">Задачи</a>
                <a href="/birthdays">Дни рождения</a>
            </c:if>
        </nav>
    </header>
    <main class="main-container">
