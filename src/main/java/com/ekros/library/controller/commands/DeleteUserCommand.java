package com.ekros.library.controller.commands;

import com.ekros.library.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ICommand {

    private final UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String href = request.getParameter("href");
        userService.deleteUser(Integer.parseInt(id));
        return href;
    }
}
