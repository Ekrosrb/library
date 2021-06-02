package com.ekros.library.controller.commands.librarian;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.Status;
import com.ekros.library.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class LibrarianCommand implements ICommand {

    private final OrderService orderService;

    public LibrarianCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {

        String from = request.getParameter("from");
        String type = request.getParameter("type");

        if(from == null || from.isEmpty()){
            from = "0";
        }

        if(type == null || type.isEmpty()){
            type = Status.PENDING.name();
        }

        request.setAttribute("type", type);

        if(type.equals("info")){
            return getInfo(request);
        }

        Status status = Status.valueOf(type);

        request.setAttribute("orders", orderService.getOrdersByStatus(status, Integer.parseInt(from)));
        request.setAttribute("pages", CommandUtils.getPages(orderService.getStatusCount(status)));

        return Path.LIBRARIAN_PAGE;
    }

    private String getInfo(HttpServletRequest request) throws SQLException {
        String id = request.getParameter("id");
        if(!CommandUtils.validateId(id)){
            request.setAttribute("message", "Order '" + id + "' not found!");
            return Path.LIBRARIAN_PAGE;
        }
        request.setAttribute("order", orderService.getOrderInfo(Integer.parseInt(id)));
        return Path.LIBRARIAN_PAGE;
    }



}
