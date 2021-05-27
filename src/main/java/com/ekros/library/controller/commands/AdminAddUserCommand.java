package com.ekros.library.controller.commands;

import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.UserService;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AdminAddUserCommand implements ICommand{

    private final UserService userService;

    public AdminAddUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception{

        User reqUser = CommandUtils.getUserFromRequest(request);
        String returnPage = "/library/admin";
        String messageName = "message";
        if(!CommandUtils.validateUser(reqUser, request)){
            return returnPage;
        }

        try {
            userService.addUser(reqUser);
        }catch (SQLException e) {
            request.setAttribute(messageName, "Something was wrong :(");
        }
        return returnPage;
    }
}
