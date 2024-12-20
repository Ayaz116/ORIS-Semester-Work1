package ru.itis.servlet;

import ru.itis.dto.AuthResponse;
import ru.itis.dto.SignInRequest;
import ru.itis.filter.AuthFilter;
import ru.itis.mapper.impl.UserMapperImpl;
import ru.itis.repository.impl.UserRepositoryImpl;
import ru.itis.service.UserService;
import ru.itis.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl(new UserRepositoryImpl(), new UserMapperImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Убираем атрибут errorMessage при обновлении страницы
        req.setAttribute("errorMessage", null);
        req.getRequestDispatcher("jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SignInRequest signInRequest = SignInRequest.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        AuthResponse authResponse = userService.signIn(signInRequest);
        if (authResponse.getStatus() == 0) {
            HttpSession session = req.getSession(true);
            session.setAttribute(AuthFilter.AUTHORIZATION, true);
            session.setAttribute("userId", authResponse.getUser().getId());
            session.setAttribute("userName", authResponse.getUser().getNickname());
            resp.sendRedirect(req.getContextPath() + "/dashboard");
        } else if (authResponse.getStatus() == 50) {
            resp.sendRedirect(req.getContextPath() + "/error?err=" + authResponse.getStatusDesc());
        } else {
            req.setAttribute("errorMessage", "Неверные данные");
            req.getRequestDispatcher("/jsp/signIn.jsp").forward(req, resp);
        }

    }

}
