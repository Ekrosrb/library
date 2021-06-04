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
        }else if(type.equals("user")){
            return getUserOrdersInfo(request, Integer.parseInt(from));
        }

        Status status = Status.valueOf(type);

        request.setAttribute("orders", orderService.getOrdersByStatus(status, Integer.parseInt(from)));
        request.setAttribute("count", orderService.getStatusCount(status));
        request.setAttribute("from", Integer.parseInt(from));

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

    private String getUserOrdersInfo(HttpServletRequest request, int from) throws SQLException {
        String id = request.getParameter("id");
        if(!CommandUtils.validateId(id)){
            request.setAttribute("message", "User '" + id + "' not found!");
            return Path.LIBRARIAN_PAGE;
        }
        request.setAttribute("orders", orderService.getUserOrders(Integer.parseInt(id), from));
        request.setAttribute("count", orderService.getUserOrdersCount(Integer.parseInt(id)));
        request.setAttribute("from", from);

        return Path.LIBRARIAN_PAGE;
    }



}
