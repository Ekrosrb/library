package com.ekros.library.controller.commands;

import com.ekros.library.dao.Role;
import com.ekros.library.model.User;
import com.ekros.library.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SiginCommand implements ICommand{

    private final UserService userService;
    private final Logger log = Logger.getLogger(SiginCommand.class);
    public SiginCommand(){
        userService = UserService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        log.debug("Sigin command");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        final String invalidPage = "/sigin";

        String messageName = "message";
        if(!CommandUtils.validate(email, password)){
            request.setAttribute(messageName, "Email or password is empty!");
            return invalidPage;
        }

        try {
            User user = new User(email, DigestUtils.md5Hex(password), Role.USER);
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
