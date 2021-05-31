package com.ekros.library.controller.filters;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.AuthUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        AuthUser authUser = (AuthUser) ((HttpServletRequest)servletRequest).getSession().getAttribute("auth");
        if(authUser != null){
            CommandUtils.setMessage(servletRequest, "You are already authorize!");
            servletRequest.getServletContext().getRequestDispatcher(Path.MAIN_PAGE).forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
