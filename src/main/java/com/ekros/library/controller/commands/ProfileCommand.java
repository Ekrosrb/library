package com.ekros.library.controller.commands;

import com.ekros.library.model.dao.impl.UserRepo;
import com.ekros.library.model.dao.interfaces.IUserRepo;
import com.ekros.library.model.entity.AuthUser;
import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfileCommand implements ICommand {

    private final UserService userService;

    public ProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("auth");
        if(authUser == null){
            return "redirect:/";
        }

        User user = userService.getUserById(authUser.getUserId());
        session.setAttribute("firstName", user.getFirstName());
        session.setAttribute("lastName", user.getLastName());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("phone", user.getPhone());
        session.setAttribute("birthday", user.getBirthday());
        return "/profile";
    }
}
