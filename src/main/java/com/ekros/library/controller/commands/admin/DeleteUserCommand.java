package com.ekros.library.controller.commands.admin;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
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
        if(id == null){
            CommandUtils.setMessage(request, "Parameter id not found!");
            return Path.ERROR_PAGE;
        }
        userService.deleteUser(Integer.parseInt(id));
        return Path.REDIRECT_ADMIN_PAGE;
    }
}
