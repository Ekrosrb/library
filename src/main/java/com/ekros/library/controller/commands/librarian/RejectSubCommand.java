package com.ekros.library.controller.commands.librarian;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.Status;
import com.ekros.library.model.entity.Order;
import com.ekros.library.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class RejectSubCommand implements ICommand {

    private final OrderService orderService;

    public RejectSubCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");

        if(!CommandUtils.validateId(id)){
            CommandUtils.setMessage(request, "Parameter id not found!");
            return Path.ERROR_PAGE;
        }

        Order order = orderService.getSub(Integer.parseInt(id));
        request.setAttribute("type", "pending");
        if(CommandUtils.validateOrder(order, Status.PENDING)){
            order.setStatus(Status.REJECTED);
            orderService.updateSub(order);
            return Path.LIBRARY_LIBRARIAN;
        }


        CommandUtils.setMessage(request, "Request has already been processed!");
        return Path.LIBRARY_LIBRARIAN;
    }
}
