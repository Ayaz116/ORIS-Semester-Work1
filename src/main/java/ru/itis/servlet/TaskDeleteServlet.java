package ru.itis.servlet;

import ru.itis.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/taskDelete")
public class TaskDeleteServlet extends HttpServlet {

    private TaskService taskService;

    @Override
    public void init() throws ServletException {
        this.taskService = (TaskService) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isBlank()) {
            HttpSession session = req.getSession();
            Long userId = (Long) session.getAttribute("userId");
            Integer id = Integer.valueOf(idParam);
            taskService.deleteTask(id, userId);
        }
        resp.sendRedirect("/dashboard");
    }
}
