package com.ekros.library.controller.commands.admin;

import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.Role;
import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserCommand implements ICommand {

    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        String block = request.getParameter("block");

        if(id == null || id.isEmpty()) {
            request.setAttribute("message", "Field id not found!");
            return Path.ERROR_PAGE;
        }
        User user = userService.getUserById(Integer.parseInt(id));

        if(firstName != null){
            user.setFirstName(firstName);
        }
        if(lastName != null){
            user.setLastName(lastName);
        }
        if(password != null){
            user.setPassword(password);
        }
        if(phone != null){
            user.setPhone(phone);
        }
        if(role != null){
            user.setRole(Role.valueOf(role));
        }
        if(block != null){
            user.setBlock(Boolean.parseBoolean(block));
        }
        userService.updateUser(user);
        return Path.REDIRECT_ADMIN_PAGE;
    }
}
