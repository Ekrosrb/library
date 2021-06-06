package com.ekros.library.controller.commands.admin;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AdminCommand implements ICommand {

    private final UserService userService;

    public AdminCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String from = request.getParameter("from");
        String id = request.getParameter("id");
        if(from == null) {
            from = "0";
        }

        if(CommandUtils.validateId(id)){
            request.setAttribute("userList", userService.getSingleUserPage(Integer.parseInt(id)));
            return Path.ADMIN_PAGE;
        }

        request.setAttribute("userList", userService.getUsersPage(Integer.parseInt(from)));
        request.setAttribute("count", userService.getCount());
        request.setAttribute("from", Integer.parseInt(from));
        return Path.ADMIN_PAGE;
    }
}
