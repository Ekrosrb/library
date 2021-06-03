package com.ekros.library.controller.commands.user;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.AuthUser;
import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.OrderService;
import com.ekros.library.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfileCommand implements ICommand {

    private final UserService userService;
    private final OrderService orderService;

    public ProfileCommand(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("auth");
        String from = request.getParameter("from");

        if(from == null || from.isEmpty()){
            from = "0";
        }

        User user = userService.getUserById(authUser.getUserId());
        session.setAttribute("firstName", user.getFirstName());
        session.setAttribute("lastName", user.getLastName());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("phone", user.getPhone());
        session.setAttribute("birthday", user.getBirthday());
        request.setAttribute("count", orderService.getUserOrdersCount(user.getId()));
        request.setAttribute("from", Integer.parseInt(from));
        request.setAttribute("subList", orderService.getUserSubs(user.getId(), Integer.parseInt(from)));

        return Path.PROFILE;
    }
}
