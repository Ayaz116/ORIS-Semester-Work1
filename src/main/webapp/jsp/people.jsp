<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Люди" scope="request"/>
<jsp:include page="header.jsp" />

<h1>Люди и их дни рождения</h1>
<form action="/people" method="post">
    <label>Имя: <input type="text" name="name" required></label>
    <label>Дата рождения: <input type="date" name="birth_date" required></label>
    <input type="submit" value="Добавить">
</form>
<ul>
    <c:forEach var="person" items="${people}">
        <li>${person.name} - ${person.birthDate}</li>
    </c:forEach>
</ul>

<jsp:include page="footer.jsp" />
