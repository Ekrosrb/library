package com.ekros.library.controller.commands.admin;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.UserService;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AdminAddUserCommand implements ICommand {

    private final UserService userService;

    public AdminAddUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception{

        User reqUser = CommandUtils.getUserFromRequest(request);
        if(!CommandUtils.validateUser(reqUser, request)){
            return Path.LIBRARY_ADMIN;
        }

        try {
            userService.addUser(reqUser);
        }catch (SQLException e) {
            CommandUtils.setMessage(request, "Something was wrong :(");
            return Path.LIBRARY_ADMIN;
        }

        return Path.REDIRECT_ADMIN_PAGE;

    }
}
