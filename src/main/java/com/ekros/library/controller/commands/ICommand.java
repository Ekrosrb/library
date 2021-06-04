package com.ekros.library.controller.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * Pattern command used to add server commands
 * @author ekros
 * */
public interface ICommand {
    /**
     * The method contains the logic of the command being executed
     * @param request - request to receive and send parameters
     * @return link to jump after command execution
     * @throws Exception the user will be shown the corresponding page with a message that something went wrong
     * */
    String execute(HttpServletRequest request) throws Exception;
}
