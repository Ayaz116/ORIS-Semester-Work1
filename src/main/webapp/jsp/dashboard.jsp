<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="Задачи и Дни Рождения" scope="request" />
<jsp:include page="header.jsp" />

<div class="dashboard-container">
    <div class="task-section">
        <h1>Ваши задачи</h1>
        <div class="toolbar">
            <label>Сортировать по:
                <select name="sort" id="sortSelect">
                    <option value="priority" ${param.sort == 'priority' ? 'selected' : ''}>Приоритет</option>
                    <option value="dueDate" ${param.sort == 'dueDate' ? 'selected' : ''}>Срок выполнения</option>
                    <option value="creationDate" ${param.sort == 'creationDate' ? 'selected' : ''}>Дата создания</option>
                </select>
            </label>
            <label style="margin-left: 15px;">
                <input type="checkbox" name="hideCompleted" id="hideCompletedCheckbox" ${param.hideCompleted == 'on' ? 'checked' : ''}> Скрыть завершенные
            </label>
            <a href="/editor" class="button" style="margin-left:auto;">Создать задачу</a>
        </div>
        <div class="task-grid" id="taskGrid">
            <c:forEach var="task" items="${tasks}">
                <c:set var="dueDateStr" value=""/>
                <c:if test="${not empty task.dueDate}">
                    <c:set var="dueDateStr" value="${task.dueDate}" />
                </c:if>
                <div class="task-card ${task.status}"
                     data-id="${task.id}"
                     data-priority="${task.priority}"
                     data-status="${task.status}"
                     data-duedate="${dueDateStr}">
                    <h3>${task.title}</h3>
                    <p>${task.description}</p>
                    <p>Приоритет: ${task.priority}</p>
                    <p>Срок:
                        <c:choose>
                            <c:when test="${not empty task.dueDate}">
                                <fmt:formatDate value="${task.dueDate}" pattern="yyyy-MM-dd HH:mm" />
                            </c:when>
                            <c:otherwise>Без срока</c:otherwise>
                        </c:choose>
                    </p>
                    <p>Статус: ${task.status}</p>
                    <div class="actions">
                        <a href="/editor?id=${task.id}" class="edit-btn">✏️ Редактировать</a>
                        <a href="/taskDelete?id=${task.id}" class="delete-btn">🗑️ Удалить</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="birthday-section">
        <h2>Ближайшие дни рождения</h2>
        <ul>
            <c:forEach var="birthday" items="${upcomingBirthdays}">
                <li>
                    <span>${birthday.name}</span> - через <b>${birthday.daysToBirthday}</b> дней, исполнится <b>${birthday.upcomingAge}</b> лет
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<jsp:include page="footer.jsp" />

<script src="/js/dashboard.js"></script>
