package com.ekros.library.controller.commands;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ICommand{

    private final Logger log = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws ServletException {

        log.info("Command: logout");
        request.getSession().invalidate();
        log.info("Session closed.");
        return "/";
    }
}
