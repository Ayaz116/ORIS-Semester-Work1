package ru.itis.servlet;

import ru.itis.model.Task;
import ru.itis.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private TaskService taskService;

    @Override
    public void init() throws ServletException {
        taskService = (TaskService) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sort = req.getParameter("sort");
        if (sort == null) sort = "priority";

        boolean hideCompleted = "on".equals(req.getParameter("hideCompleted"));

        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            resp.sendRedirect("/signIn");
            return; // Если пользователь не авторизован
        }

        List<Task> tasks = taskService.getAllTasks(sort, hideCompleted, userId);
        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("/jsp/dashboard.jsp").forward(req, resp);
    }

}
