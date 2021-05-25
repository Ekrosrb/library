package com.ekros.library.controller.commands;

import com.ekros.library.model.entity.Role;
import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.SQLException;

public class SiginCommand implements ICommand{

    private final UserService userService;
    private final Logger log = Logger.getLogger(SiginCommand.class);

    public SiginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request){
        log.debug("Sigin command");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Date birthday = Date.valueOf(request.getParameter("birthday"));
        String phone = request.getParameter("phone");

        log.info("----NEW USER INFO----");
        log.info(firstName);
        log.info(lastName);
        log.info(email);
        log.info(birthday);
        log.info(phone);
        log.info("---------------------");
        final String invalidPage = "/";

        String messageName = "message";
        if(CommandUtils.validate(email, password)){
            request.setAttribute(messageName, "Email or password is empty!");
            return invalidPage;
        }

        if(firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() || birthday == null || phone == null || phone.isEmpty()){
            request.setAttribute(messageName, "Fill in all the fields!");
        }

        try {
            User user = new User(firstName, lastName, email, DigestUtils.md5Hex(password), birthday, phone, Role.USER);
            userService.addUser(user);
            if (!CommandUtils.updateSession(request, user)) {
                request.setAttribute(messageName, "You are already authorized!");
                return invalidPage;
            }
        }catch (SQLException e) {
            log.error(e.getMessage());
            request.setAttribute(messageName, "Something was wrong :(");
            return "/error";
        }
        log.info("Successful sigin!");
        return "/";
    }
}
