package com.ekros.library.controller.servlets;

import com.ekros.library.controller.commands.*;
import com.ekros.library.model.service.BookService;
import com.ekros.library.model.service.UserService;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LibraryServlet extends HttpServlet {

    private Map<String, ICommand> commands;
    private Logger log;
    private final UserService userService = new UserService();
    private final BookService bookService = new BookService();
    @Override
    public void init() throws ServletException {
        log = Logger.getLogger(LibraryServlet.class);
        commands = new HashMap<>();
        commands.put("login", new LoginCommand(userService));
        commands.put("logout", new LogoutCommand());
        commands.put("sigin", new SiginCommand(userService));
        commands.put("locale", new LocaleCommand());
        commands.put("profile", new ProfileCommand(userService));
        commands.put("books", new SearchBookCommand(bookService));
        commands.put("admin", new AdminCommand(userService));
        commands.put("updateUser", new UpdateUserCommand(userService));
        commands.put("deleteUser", new DeleteUserCommand(userService));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("--------GET--------");
        handler(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("--------POST--------");
        handler(req, resp);
    }


    private void handler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            String path = req.getRequestURI();
            path = path.replaceAll(".*/library/", "");

            log.info("Command: " + path);
            ICommand command = commands.get(path);

            path = command.execute(req);
            if(path.contains("redirect:")) {
                path = path.replaceAll("redirect:", "");
                log.info("Send redirect: " + path);
                resp.sendRedirect(path);
            }else{
                log.info("Forward: " + path);
                req.getServletContext().getRequestDispatcher(path).forward(req, resp);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            resp.sendRedirect("/error");
        }
    }

    @Override
    public void destroy() {

    }

}
