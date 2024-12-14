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
        try {
            req.setAttribute("errorMessage", null);
            req.getRequestDispatcher("jsp/signUp.jsp").forward(req, resp);
        } catch (Exception e) {
            if (e.getMessage().contains("Failed to obtain JDBC Connection")) {
                resp.sendRedirect("/error?err=Database Connection Failed");
            } else {
                resp.sendRedirect("/error?err=Database Error: " + e.getMessage());
            }
        }

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
            resp.sendRedirect("/signIn");
        } else if (authResponse.getStatus() == 50) {
            resp.sendRedirect("/error?err=" + authResponse.getStatusDesc());
        } else {
            req.setAttribute("errorMessage", "Неверные данные");
            req.getRequestDispatcher("jsp/signUp.jsp").forward(req, resp);
        }

    }


}
