package ru.itis.servlet;

import ru.itis.model.Birthday;
import ru.itis.service.BirthdayService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/birthdays")
public class BirthdaysServlet extends HttpServlet {

    private BirthdayService birthdayService;

    @Override
    public void init() throws ServletException {
        this.birthdayService = (BirthdayService) getServletContext().getAttribute("birthdayService");
        if (this.birthdayService == null) {
            throw new ServletException("BirthdayService is not initialized in the context.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Birthday> birthdays = birthdayService.getAllBirthdays();
        req.setAttribute("birthdays", birthdays);
        req.getRequestDispatcher("/jsp/birthdays.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String birthDate = req.getParameter("birth_date");

        Birthday birthday = Birthday.builder()
                .name(name)
                .birthDate(Date.valueOf(birthDate))
                .build();

        birthdayService.addBirthday(birthday);
        resp.sendRedirect("/birthdays");
    }
}
