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
        // Получаем текущую сессию (если есть)
        HttpSession session = req.getSession(false);
        if (session != null) {
            // Удаляем атрибут авторизации
            session.removeAttribute(AuthFilter.AUTHORIZATION);
            session.removeAttribute("userName");
            // Инвалидируем сессию полностью, если нужно
            session.invalidate();
        }
        // Перенаправляем на страницу входа или на главную
        resp.sendRedirect("/");
    }
}
