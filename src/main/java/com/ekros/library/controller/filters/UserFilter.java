package com.ekros.library.controller.filters;

import com.ekros.library.dao.Role;
import com.ekros.library.model.AuthUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        AuthUser authUser = (AuthUser) ((HttpServletRequest)servletRequest).getSession().getAttribute("auth");
        if(authUser == null){
            servletRequest.setAttribute("message", "You are not authorize!");
            servletRequest.getServletContext()
                    .getRequestDispatcher("/login")
                    .forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
