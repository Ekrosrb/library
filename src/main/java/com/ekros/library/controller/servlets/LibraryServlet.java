package com.ekros.library.controller.servlets;

import com.ekros.library.controller.commands.*;
import com.ekros.library.controller.commands.admin.*;
import com.ekros.library.controller.commands.guest.SiginCommand;
import com.ekros.library.controller.commands.librarian.AddFineCommand;
import com.ekros.library.controller.commands.librarian.ChangeStatusCommand;
import com.ekros.library.controller.commands.user.*;
import com.ekros.library.controller.commands.guest.LocaleCommand;
import com.ekros.library.controller.commands.guest.LoginCommand;
import com.ekros.library.controller.commands.guest.SearchBookCommand;
import com.ekros.library.controller.commands.librarian.LibrarianCommand;
import com.ekros.library.model.service.BookService;
import com.ekros.library.model.service.OrderService;
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
    private final OrderService orderService = new OrderService();

    @Override
    public void init() throws ServletException {
        log = Logger.getLogger(LibraryServlet.class);
        commands = new HashMap<>();
        commands.put("login", new LoginCommand(userService));
        commands.put("logout", new LogoutCommand());
        commands.put("sigin", new SiginCommand(userService));
        commands.put("locale", new LocaleCommand());
        commands.put("profile", new ProfileCommand(userService, orderService));
        commands.put("books", new SearchBookCommand(bookService));
        commands.put("admin", new AdminCommand(userService));
        commands.put("updateUser", new UpdateUserCommand(userService));
        commands.put("deleteUser", new DeleteUserCommand(userService));
        commands.put("adminAddUser", new AdminAddUserCommand(userService));
        commands.put("adminUpdateBook", new UpdateBookCommand(bookService));
        commands.put("addBook", new AddBookCommand(bookService));
        commands.put("deleteBook", new DeleteBookCommand(bookService));
        commands.put("orderBook", new AddOrderCommand(orderService));
        commands.put("librarian", new LibrarianCommand(orderService));
        commands.put("changeStatus", new ChangeStatusCommand(orderService));
        commands.put("payFine", new PayFineCommand(orderService));
        commands.put("addFine", new AddFineCommand(orderService));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("--------GET--------");
        handler(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("--------POST--------");
        handler(req, resp);
    }


    private void handler(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        try {
            String path = req.getRequestURI();
            path = path.replaceAll(".*/library/", "");

            log.info("Command: " + path);
            ICommand command = commands.get(path);

            path = command.execute(req);
            if(path.contains(Path.REDIRECT)) {
                path = path.replaceAll(Path.REDIRECT, "");
                log.info("Send redirect: " + path);
                resp.sendRedirect(path);
            }else{
                log.info("Forward: " + path);
                CommandUtils.forward(req, resp, path);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            resp.sendRedirect(Path.ERROR_PAGE);
        }
    }

    @Override
    public void destroy() {

    }

}
