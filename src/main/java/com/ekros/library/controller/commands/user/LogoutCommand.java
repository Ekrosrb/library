package com.ekros.library.controller.commands.user;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements ICommand {

    private final Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws ServletException {

        HttpSession session = request.getSession(false);
        if(session != null){
            session.setAttribute("auth", null);
            log.info("User logged out.");
        }
        return CommandUtils.searchBookRequest(request);
    }
}
