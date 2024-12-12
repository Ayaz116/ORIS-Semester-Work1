<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="${task == null ? 'Создать задачу' : 'Редактировать задачу'}" scope="request"/>
<jsp:include page="header.jsp" />

<h1>${task == null ? "Создать задачу" : "Редактировать задачу"}</h1>
<form action="/editor" method="post">
    <input type="hidden" name="id" value="${task.id}" />
    <label>Название: <input type="text" name="title" value="${task.title}" required></label><br>
    <label>Описание: <textarea name="description">${task.description}</textarea></label><br>
    <label>Приоритет:
        <select name="priority">
            <option value="low" ${task.priority == "low" ? "selected" : ""}>Низкий</option>
            <option value="medium" ${task.priority == "medium" ? "selected" : ""}>Средний</option>
            <option value="high" ${task.priority == "high" ? "selected" : ""}>Высокий</option>
        </select>
    </label><br>
    <label>Срок выполнения: <input type="datetime-local" name="due_date" value="${task.dueDate}"></label><br>
    <label>Статус:
        <select name="status">
            <option value="pending" ${task.status == "pending" ? "selected" : ""}>Ожидает</option>
            <option value="done" ${task.status == "done" ? "selected" : ""}>Выполнено</option>
            <option value="archived" ${task.status == "archived" ? "selected" : ""}>В архиве</option>
            <option value="overdue" ${task.status == "overdue" ? "selected" : ""}>Просрочено</option>
        </select>
    </label><br>
    <input type="submit" value="Сохранить">
    <a href="/dashboard">Отмена</a>
</form>

<jsp:include page="footer.jsp" />
