package com.example.springboot.filter;

import com.example.springboot.manager.UserManager;
import com.example.springboot.security.Authentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter extends HttpFilter {
    private final UserManager userManager;

    @Override
    protected void doFilter(final HttpServletRequest req, final HttpServletResponse res, final FilterChain chain) throws IOException, ServletException, ServletException {
        final String login = req.getHeader("X-Login");
        final String password = req.getHeader("X-Password");
        log.debug("login: {}, password: {}", login, password);
        if (login == null) {
            res.setStatus(401);
            res.getWriter().write("Not authenticated");
            return;
        }

        if (password == null) {
            res.setStatus(401);
            res.getWriter().write("Not authenticated");
            return;
        }

        try {
            final Authentication authentication = userManager.authenticateByLoginAndPassword(login, password);
            req.setAttribute("authentication", authentication);
        } catch (RuntimeException e) {
            res.setStatus(401);
            res.getWriter().write("Not authenticated");
            return;
        }

        chain.doFilter(req, res);
    }
}
