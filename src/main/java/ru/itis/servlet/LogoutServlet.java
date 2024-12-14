package ru.itis.servlet;

import ru.itis.filter.AuthFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute(AuthFilter.AUTHORIZATION);
            session.removeAttribute("userName");
            session.invalidate();
        }
        resp.sendRedirect("/");
    }
}
