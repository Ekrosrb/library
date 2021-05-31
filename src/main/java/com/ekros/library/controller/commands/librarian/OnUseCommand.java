package com.ekros.library.controller.commands.librarian;

import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class OnUseCommand implements ICommand {

    private final OrderService orderService;

    public OnUseCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {


        return null;
    }
}
