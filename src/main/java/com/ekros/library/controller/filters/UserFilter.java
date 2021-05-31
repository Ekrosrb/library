package com.ekros.library.controller.filters;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.AuthUser;
import com.ekros.library.model.entity.Role;

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
        if(authUser == null ||
                authUser.getRole() != Role.USER &&
                authUser.getRole() != Role.LIBRARIAN &&
                authUser.getRole() != Role.ADMIN){

            CommandUtils.setMessage(servletRequest, "You are not authorize!");
            servletRequest.getServletContext()
                    .getRequestDispatcher(Path.MAIN_PAGE)
                    .forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
