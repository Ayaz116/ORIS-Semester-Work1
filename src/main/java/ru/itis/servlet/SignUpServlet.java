package ru.itis.servlet;

import ru.itis.dto.AuthResponse;
import ru.itis.dto.SignUpRequest;
import ru.itis.mapper.impl.UserMapperImpl;
import ru.itis.repository.impl.UserRepositoryImpl;
import ru.itis.service.UserService;
import ru.itis.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl(new UserRepositoryImpl(), new UserMapperImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Убираем атрибут errorMessage при обновлении страницы
        req.setAttribute("errorMessage", null);
        req.getRequestDispatcher("jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email(req.getParameter("email"))
                .nickname(req.getParameter("nickname"))
                .password(req.getParameter("password"))
                .build();

        AuthResponse authResponse = userService.signUp(signUpRequest);
        if (authResponse.getStatus() == 0) {
            // Успешная регистрация
            resp.sendRedirect("/signIn");
        } else if (authResponse.getStatus() == 50) {
            // Техническая ошибка
            resp.sendRedirect("/error?err=" + authResponse.getStatusDesc());
        } else {
            // Пользовательская ошибка (всегда показываем "Неверные данные")
            req.setAttribute("errorMessage", "Неверные данные");
            req.getRequestDispatcher("jsp/signUp.jsp").forward(req, resp);
        }
    }


}
