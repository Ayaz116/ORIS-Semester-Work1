package ru.itis.servlet;

import ru.itis.model.Task;
import ru.itis.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/editor")
public class EditorServlet extends HttpServlet {

    private TaskService taskService;

    @Override
    public void init() throws ServletException {
        taskService = (TaskService) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            Long userId = (Long) session.getAttribute("userId");

            String idParam = req.getParameter("id");

            if (idParam != null && !idParam.isBlank()) {
                Integer id = Integer.valueOf(idParam);
                Task task = taskService.getTaskById(id, userId);
                req.setAttribute("task", task);
            }
            req.getRequestDispatcher("/jsp/editor.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect("/error?err=" + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            resp.sendRedirect("/signIn");
            return;
        }

        String idParam = req.getParameter("id");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String priority = req.getParameter("priority");
        String dueDateStr = req.getParameter("due_date");
        String status = req.getParameter("status");

        Timestamp dueDate = null;
        if (dueDateStr != null && dueDateStr.contains("T")) {
            dueDateStr = dueDateStr.replace("T", " ") + ":00";
            dueDate = Timestamp.valueOf(dueDateStr);
        }

        Task task = Task.builder()
                .title(title)
                .description(description)
                .priority(priority)
                .dueDate(dueDate)
                .status(status)
                .userId(userId)
                .build();

        if (idParam != null && !idParam.isBlank()) {
            task.setId(Integer.valueOf(idParam));
            taskService.updateTask(task);
        } else {
            taskService.createTask(task);
        }
        resp.sendRedirect("/dashboard");

    }
}
