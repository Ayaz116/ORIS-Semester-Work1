package ru.itis.servlet;

import ru.itis.service.BirthdayService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/birthdays/delete")
public class BirthdayDeleteServlet extends HttpServlet {

    private BirthdayService birthdayService;

    @Override
    public void init() throws ServletException {
        birthdayService = (BirthdayService) getServletContext().getAttribute("birthdayService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isBlank()) {
            HttpSession session = req.getSession();
            Long userId = (Long) session.getAttribute("userId");
            Long id = Long.valueOf(idParam);
            birthdayService.deleteBirthday(id, userId);
        }
        resp.sendRedirect(req.getContextPath() + "/birthdays");
    }
}
