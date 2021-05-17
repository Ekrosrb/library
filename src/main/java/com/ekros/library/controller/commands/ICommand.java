package com.ekros.library.controller.commands;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    String execute(HttpServletRequest request) throws Exception;
}
