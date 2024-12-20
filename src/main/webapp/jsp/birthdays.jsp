<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="Дни рождения" scope="request" />
<jsp:include page="header.jsp" />

<div class="page-container">
    <div class="section">
        <h1>Дни рождения</h1>
        <form action="${pageContext.request.contextPath}/birthdays" method="post" class="birthday-form">
            <label for="name">Имя:</label>
            <input type="text" id="name" name="name" required>

            <label for="birth_date">Дата рождения:</label>
            <input type="date" id="birth_date" name="birth_date" required>

            <input type="submit" value="Добавить" class="submit-btn">
        </form>

        <div class="birthday-list">
            <c:forEach var="birthday" items="${birthdays}">
                <div class="birthday-item">
                    <span>${birthday.name} - ${birthday.birthDate}</span>
                    <a href="${pageContext.request.contextPath}/birthdays/delete?id=${birthday.id}" class="delete-btn">Удалить</a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />
</main>
</div>
</body>
</html>
