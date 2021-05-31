package com.ekros.library.controller.commands.librarian;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.Status;
import com.ekros.library.model.entity.Order;
import com.ekros.library.model.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AcceptSubCommand implements ICommand {

    private final OrderService orderService;
    private final Logger log = Logger.getLogger(AcceptSubCommand.class);
    public AcceptSubCommand(OrderService orderService) {
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
            order.setStatus(Status.ACCEPTED);
            orderService.updateSub(order);
            log(order);
            return Path.LIBRARY_LIBRARIAN;
        }

        CommandUtils.setMessage(request, "Request has already been processed!");
        return Path.LIBRARY_LIBRARIAN;
    }

    private void log(Order sub){
        log.info("----Accept order----");
        log.info("Id: " + sub.getId());
        log.info("User: " + sub.getUserId());
        log.info("Book: " + sub.getBookId());
        log.info("Term: " + sub.getTerm());
        log.info("Status: " + sub.getStatus());
        log.info("--------------------");
    }
}
