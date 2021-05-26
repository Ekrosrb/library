package com.ekros.library.controller.commands;

import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminCommand implements ICommand{

    private final UserService userService;

    public AdminCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String from = (String) request.getAttribute("from");
        List<User> users;
        if(from == null){
            users = userService.getUsersPage(0);
        }else{
            users = userService.getUsersPage(Integer.parseInt(from));
        }
        request.setAttribute("userList", users);
        request.setAttribute("count", userService.getCount());
        request.setAttribute("pages", 1 + users.size()/20);
        return "/admin/adminPage.jsp";
    }
}
