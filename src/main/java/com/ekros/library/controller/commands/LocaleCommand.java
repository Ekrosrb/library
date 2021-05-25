package com.ekros.library.controller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class LocaleCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request) throws Exception {

        String locale = request.getParameter("locale");
        HttpSession session = request.getSession();

        if(locale == null){
            request.setAttribute("message", "Incorrect locale name!");
            return "/error";
        }

        session.setAttribute("locale", locale);

        Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", locale);
        return "redirect:/";
    }
}
