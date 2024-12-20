package ru.itis.servlet;

import ru.itis.model.Birthday;
import ru.itis.model.Task;
import ru.itis.service.BirthdayService;
import ru.itis.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private TaskService taskService;
    private BirthdayService birthdayService;

    @Override
    public void init() throws ServletException {
        taskService = (TaskService) getServletContext().getAttribute("taskService");
        birthdayService = (BirthdayService) getServletContext().getAttribute("birthdayService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/signIn");
            return;
        }

        String sort = req.getParameter("sort");
        if (sort == null) sort = "priority";

        boolean hideCompleted = "on".equals(req.getParameter("hideCompleted"));

        List<Task> tasks = taskService.getAllTasks(sort, hideCompleted, userId);
        List<Birthday> upcomingBirthdays = birthdayService.getUpcomingBirthdays(userId);

        req.setAttribute("tasks", tasks);
        req.setAttribute("upcomingBirthdays", upcomingBirthdays);
        req.getRequestDispatcher("/jsp/dashboard.jsp").forward(req, resp);


    }
}