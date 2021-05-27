package com.ekros.library.controller.commands;

import com.ekros.library.model.entity.AuthUser;
import com.ekros.library.model.entity.Role;
import com.ekros.library.model.entity.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.sql.Date;

public class CommandUtils {

    public static boolean validate(String email, String password){
        return !(email == null || password == null || email.isEmpty() || password.isEmpty());
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

    public static boolean validateUser(User reqUser, HttpServletRequest request){
        if(!CommandUtils.validate(reqUser.getEmail(), reqUser.getPassword())){
            request.setAttribute("message", "Email or password is empty!");
            return false;
        }

        if(reqUser.getFirstName() == null || reqUser.getFirstName().isEmpty() ||
                reqUser.getLastName() == null || reqUser.getLastName().isEmpty() ||
                reqUser.getBirthday() == null || reqUser.getPhone() == null || reqUser.getPhone().isEmpty()){
            request.setAttribute("message", "Fill in all the fields!");
            return false;
        }
        return true;
    }

    public static User getUserFromRequest(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");

        return new User(firstName, lastName,
                email, password,
                birthday, phone,
                role != null?Role.valueOf(role):Role.USER,
                false);
    }
}
