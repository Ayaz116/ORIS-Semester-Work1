package ru.itis.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final List<String> PROTECTED_URIS = List.of("/logout", "/dashboard", "/editor", "/birthdays");
    private static final List<String> AUTH_PAGES = List.of("/signIn", "/signUp");

    public static final String AUTHORIZATION = "authorization";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();

        boolean userAuth = isUserAuth(request);

        if (userAuth && isAuthPage(uri)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute(AUTHORIZATION);
                session.removeAttribute("userName");
                session.invalidate();
            }
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (!userAuth && isProtectedUri(uri)) {
            response.sendRedirect("/signIn");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isProtectedUri(String uri) {
        return PROTECTED_URIS.contains(uri);
    }

    private boolean isAuthPage(String uri) {
        return AUTH_PAGES.contains(uri);
    }

    private boolean isUserAuth(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) return false;
        Boolean flag = (Boolean) session.getAttribute(AUTHORIZATION);
        return flag != null && flag;
    }
}
