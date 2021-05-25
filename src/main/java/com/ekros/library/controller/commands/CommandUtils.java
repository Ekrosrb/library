package com.ekros.library.controller.commands;

import com.ekros.library.model.entity.AuthUser;
import com.ekros.library.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class CommandUtils {

    public static boolean validate(String email, String password){
        return email == null || password == null || email.isEmpty() || password.isEmpty();
    }

    public static boolean updateSession(HttpServletRequest request, User user) {

        HttpSession session = request.getSession();
        AuthUser authUser = (AuthUser) session.getAttribute("auth");
        if(authUser != null){
            return false;
        }
        String locale = (String) session.getAttribute("locale");
        if(locale == null){
            locale = "en";
        }
        Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", locale);
        authUser = new AuthUser(user.getId(), user.getRole());
        session.setAttribute("auth", authUser);

        return true;
    }
}
