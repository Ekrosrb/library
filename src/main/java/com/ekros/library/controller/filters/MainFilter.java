package com.ekros.library.controller.filters;

import com.ekros.library.controller.commands.Path;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MainFilter implements Filter {

    private Logger log = Logger.getLogger(MainFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        String locale = (String) servletRequest.getAttribute("locale");
        String url = ((HttpServletRequest)servletRequest).getRequestURI();

        if(locale == null){
            servletRequest.setAttribute("locale", "en");
        }

        if(url.equals(Path.INDEX_PAGE)) {
           servletRequest.getRequestDispatcher(Path.MAIN_PAGE).forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
