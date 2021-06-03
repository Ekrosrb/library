package com.ekros.library.controller.commands.user;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class PayFineCommand implements ICommand {

    private final OrderService orderService;

    public PayFineCommand(OrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public String execute(HttpServletRequest request) throws Exception {

        String id = request.getParameter("id");

        if(!CommandUtils.validateId(id)){
            CommandUtils.setMessage(request, "Incorrect id value!");
        }

        orderService.payFine(Integer.parseInt(id));

        return Path.LIBRARY_PROFILE;
    }
}
