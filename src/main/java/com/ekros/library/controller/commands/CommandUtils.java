package com.ekros.library.controller.commands;

import com.ekros.library.model.AuthUser;
import com.ekros.library.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;

public class CommandUtils {

    public static boolean validate(String email, String password){
        return email != null && password != null && !email.isEmpty() && !password.isEmpty();
    }

    public static boolean updateSession(HttpServletRequest request, User user) {
        AuthUser authUser = (AuthUser) request.getSession().getAttribute("auth");
        if(authUser != null){
            return false;
        }

        authUser = new AuthUser(user.getId(), DigestUtils.md5Hex(user.getEmail()), user.getRole());
        request.getSession().setAttribute("auth", authUser);

        return true;
    }
}
