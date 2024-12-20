<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="${task == null ? 'Создать задачу' : 'Редактировать задачу'}" scope="request" />
<jsp:include page="header.jsp" />

<div class="page-container">
    <div class="section">
        <h1>${task == null ? "Создать задачу" : "Редактировать задачу"}</h1>
        <form action="${pageContext.request.contextPath}/editor" method="post" class="task-form" style="margin-top:20px;">
            <input type="hidden" name="id" value="${task.id}" />
            <label for="title">Название:</label>
            <input type="text" id="title" name="title" value="${task.title}" required>

            <label for="description">Описание:</label>
            <textarea id="description" name="description">${task.description}</textarea>

            <label for="priority">Приоритет:</label>
            <select id="priority" name="priority">
                <option value="Низкий" ${task.priority == "Низкий" ? "selected" : ""}>Низкий</option>
                <option value="Средний" ${task.priority == "Средний" ? "selected" : ""}>Средний</option>
                <option value="Высокий" ${task.priority == "Высокий" ? "selected" : ""}>Высокий</option>
            </select>

            <label for="due_date">Срок выполнения:</label>
            <input type="datetime-local" id="due_date" name="due_date" value="${task.dueDate}">

            <label for="status">Статус:</label>
            <select id="status" name="status">
                <option value="Выполняется" ${task.status == "Выполняется" ? "selected" : ""}>Выполняется</option>
                <option value="Завершено" ${task.status == "Завершено" ? "selected" : ""}>Завершено</option>
                <option value="Просрочено" ${task.status == "Просрочено" ? "selected" : ""}>Просрочено</option>
            </select>

            <div class="form-actions" style="margin-top: 20px; display:flex; gap:15px; justify-content:center;">
                <input type="submit" value="Сохранить" class="submit-btn">
                <a href="${pageContext.request.contextPath}/dashboard" class="cancel-btn">Отмена</a>
            </div>
        </form>
    </div>
</div>

<jsp:include page="footer.jsp" />
</main>
</div>
</body>
</html>
