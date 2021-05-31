package com.ekros.library.controller.commands.guest;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class LoginCommand implements ICommand {

    private final UserService userService;
    private final Logger log = Logger.getLogger(LoginCommand.class);
    public LoginCommand(UserService userService){
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request){
        log.debug("Login command");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(!CommandUtils.validate(email, password)){
            CommandUtils.setMessage(request, "Email or password is empty!");
            return Path.MAIN_PAGE;
        }
        try {
            User user = userService.getUserByEmail(email);
            if (user == null || !user.getPassword().equals(DigestUtils.md5Hex(password))) {
                CommandUtils.setMessage(request, "Incorrect email or password!");
                return Path.MAIN_PAGE;
            }
            if(user.isBlock()){
                CommandUtils.setMessage(request, "Your account has been blocked!");
                return Path.MAIN_PAGE;
            }

            if (!CommandUtils.updateSession(request, user)) {
                CommandUtils.setMessage(request, "You are already authorized!");
                return Path.MAIN_PAGE;
            }
        }catch (SQLException e) {
            log.error(e.getMessage());
            CommandUtils.setMessage(request, "Something was wrong :(");
            return Path.ERROR_PAGE;
        }
        log.info("Successful!");
        return CommandUtils.searchBookRequest(request);
    }



}
