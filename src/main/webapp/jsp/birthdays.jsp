<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Дни рождения" scope="request"/>
<jsp:include page="header.jsp" />

<h1>Дни рождения</h1>
<form action="/birthdays" method="post">
    <label>Имя: <input type="text" name="name" required></label>
    <label>Дата рождения: <input type="date" name="birth_date" required></label>
    <input type="submit" value="Добавить">
</form>

<div class="birthday-list">
    <c:forEach var="birthday" items="${birthdays}">
        <div class="birthday-item">
            <span>${birthday.name} - ${birthday.birthDate}</span>
            <a href="/birthdays/delete?id=${birthday.id}" class="delete-btn">Удалить</a>
        </div>
    </c:forEach>
</div>

<jsp:include page="footer.jsp" />
