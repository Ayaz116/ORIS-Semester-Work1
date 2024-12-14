<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="pageTitle" value="Задачи" scope="request"/>
<jsp:include page="header.jsp" />

<div class="dashboard-container">
    <!-- Основная часть с задачами -->
    <div class="task-section">
        <h1>Ваши задачи</h1>
        <div class="toolbar">
            <form method="get" action="/dashboard" class="filter-form">
                <label>Сортировать по:
                    <select name="sort" id="sortSelect">
                        <option value="priority" ${param.sort == 'priority' ? 'selected' : ''}>Приоритет</option>
                        <option value="dueDate" ${param.sort == 'dueDate' ? 'selected' : ''}>Срок выполнения</option>
                        <option value="creationDate" ${param.sort == 'creationDate' ? 'selected' : ''}>Дата создания</option>
                    </select>
                </label>
                <label>
                    <input type="checkbox" name="hideCompleted" ${param.hideCompleted == 'on' ? 'checked' : ''}> Скрыть завершенные
                </label>
                <button type="submit">Применить</button>
                <a href="/editor" class="button">Создать задачу</a>
            </form>
        </div>

        <div class="task-grid">
            <c:forEach var="task" items="${tasks}">
                <c:if test="${!(param.hideCompleted == 'on' and task.status == 'Завершено')}">
                    <div class="task-card ${task.status}">
                        <h3>${task.title}</h3>
                        <p>${task.description}</p>
                        <p>Приоритет: ${task.priority}</p>
                        <p>Срок:
                            <c:choose>
                                <c:when test="${not empty task.dueDate}">
                                    <fmt:formatDate value="${task.dueDate}" pattern="yyyy-MM-dd HH:mm"/>
                                </c:when>
                                <c:otherwise>
                                    Без срока
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <p>Статус: ${task.status}</p>
                        <div class="actions">
                            <a href="/editor?id=${task.id}" class="edit-btn">✏️</a>
                            <a href="/taskDelete?id=${task.id}" class="delete-btn">🗑️</a>
                        </div>
                        <div class="subtasks">
                            <c:if test="${not empty task.subTasks}">
                                <details>
                                    <summary>Подзадачи (${fn:length(task.subTasks)})</summary>
                                    <ul>
                                        <c:forEach var="sub" items="${task.subTasks}">
                                            <li>${sub.title}</li>
                                        </c:forEach>
                                    </ul>
                                </details>
                            </c:if>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>

    <!-- Боковая панель с ближайшими днями рождения -->
    <aside class="birthday-section">
        <h2>Ближайшие дни рождения</h2>
        <ul>
            <c:forEach var="birthday" items="${upcomingBirthdays}">
                <li>
                    <span>${birthday.name}</span> - через <b>${birthday.daysToBirthday}</b> дней, исполнится <b>${birthday.upcomingAge}</b> лет
                </li>
            </c:forEach>
        </ul>
    </aside>
</div>

<jsp:include page="footer.jsp" />