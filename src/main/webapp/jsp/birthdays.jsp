<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Дни рождения" scope="request"/>
<jsp:include page="header.jsp" />

<h1>Список дней рождения</h1>
<form action="/birthdays" method="post">
    <label>Имя: <input type="text" name="name" required></label>
    <label>Дата рождения: <input type="date" name="birth_date" required></label>
    <input type="submit" value="Добавить">
</form>
<ul>
    <c:forEach var="birthday" items="${birthdays}">
        <li>${birthday.name} - ${birthday.birthDate}</li>
    </c:forEach>
</ul>

<jsp:include page="footer.jsp" />
