package ru.itis.servlet;

import ru.itis.model.Birthday;
import ru.itis.service.BirthdayService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/birthdays")
public class BirthdaysServlet extends HttpServlet {

    private BirthdayService birthdayService;

    @Override
    public void init() throws ServletException {
        birthdayService = (BirthdayService) getServletContext().getAttribute("birthdayService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            resp.sendRedirect("/signIn");
            return;
        }

        List<Birthday> birthdays = birthdayService.getAllBirthdays(userId);
        req.setAttribute("birthdays", birthdays);
        req.getRequestDispatcher("/jsp/birthdays.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(false);
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            resp.sendRedirect("/signIn");
            return;
        }

        String name = req.getParameter("name");
        LocalDate birthDate = LocalDate.parse(req.getParameter("birth_date"));

        Birthday birthday = Birthday.builder()
                .name(name)
                .birthDate(birthDate)
                .userId(userId)
                .build();

        birthdayService.addBirthday(birthday);
        resp.sendRedirect("/birthdays");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            resp.sendRedirect("/signIn");
            return;
        }

        Long id = Long.valueOf(req.getParameter("id"));
        birthdayService.deleteBirthday(id, userId);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
