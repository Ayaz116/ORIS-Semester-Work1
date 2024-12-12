<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Контакты</title></head>
<body>
<h1>Контакты (Дни рождения)</h1>
<ul>
  <c:forEach var="contact" items="${contacts}">
    <li>${contact.name} - ${contact.birthDate}</li>
  </c:forEach>
</ul>
<h2>Добавить контакт</h2>
<form action="/contacts" method="post">
  <label>Имя: <input type="text" name="name" required></label><br>
  <label>Дата рождения (YYYY-MM-DD): <input type="text" name="birth_date" required></label><br>
  <input type="submit" value="Добавить">
</form>
<a href="/dashboard">Назад</a>
</body>
</html>
