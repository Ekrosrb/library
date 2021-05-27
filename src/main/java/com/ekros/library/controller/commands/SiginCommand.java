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
    public String execute(HttpServletRequest request) throws Exception{
        log.debug("Sigin command");

        User reqUser = CommandUtils.getUserFromRequest(request);

        log.info("----NEW USER INFO----");
        log.info(reqUser.getFirstName());
        log.info(reqUser.getLastName());
        log.info(reqUser.getEmail());
        log.info(reqUser.getBirthday());
        log.info(reqUser.getPhone());
        log.info("---------------------");

        final String invalidPage = "/";

        String messageName = "message";
        if(!CommandUtils.validateUser(reqUser, request)){
            return invalidPage;
        }

        try {
            userService.addUser(reqUser);
            if (!CommandUtils.updateSession(request, reqUser)) {
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
