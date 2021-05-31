package com.ekros.library.controller.commands.guest;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.User;
import com.ekros.library.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SiginCommand implements ICommand {

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

        if(!CommandUtils.validateUser(reqUser, request)){
            return Path.MAIN_PAGE;
        }

        try {
            userService.addUser(reqUser);
            if (!CommandUtils.updateSession(request, reqUser)) {
                CommandUtils.setMessage(request, "You are already authorized!");
                return Path.MAIN_PAGE;
            }
        }catch (SQLException e) {
            log.error(e.getMessage());
            CommandUtils.setMessage(request, "Something was wrong :(");
            return Path.ERROR_PAGE;
        }
        log.info("Successful sigin!");
        return CommandUtils.searchBookRequest(request);
    }
}
