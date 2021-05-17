package com.ekros.library.controller.servlets;

import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.LoginCommand;
import com.ekros.library.controller.commands.LogoutCommand;
import com.ekros.library.controller.commands.SiginCommand;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthServlet extends HttpServlet {

    private Map<String, ICommand> commands;
    private Logger log;
    @Override
    public void init() throws ServletException {
        log = Logger.getLogger(AuthServlet.class);
        commands = new HashMap<>();
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("sigin", new SiginCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("--------GET--------");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            log.info("--------POST--------");

            String path = req.getRequestURI();
            path = path.replaceAll(".*/auth/", "");

            log.info("Command: " + path);
            ICommand command = commands.get(path);

            req.getServletContext().getRequestDispatcher(command.execute(req)).forward(req, resp);

        }catch (Exception e){
            log.error(e.getMessage());
            resp.sendRedirect("/error");
        }
    }



    @Override
    public void destroy() {

    }

}
