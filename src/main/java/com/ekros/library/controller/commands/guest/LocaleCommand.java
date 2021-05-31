package com.ekros.library.controller.commands.guest;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class LocaleCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) throws Exception {

        String locale = request.getParameter("locale");
        HttpSession session = request.getSession();
        if(locale == null){
           CommandUtils.setMessage(request, "Incorrect locale name!");
            return Path.ERROR_PAGE;
        }
        session.setAttribute("locale", locale);
        Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", locale);
        return Path.REDIRECT_MAIN_PAGE;
    }
}
