package com.ekros.library.controller.commands.librarian;

import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

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

        if(type == null){
            request.setAttribute("subList", orderService.getPendingSubs(Integer.parseInt(from)));
            request.setAttribute("type", "pending");
            return Path.LIBRARIAN_PAGE;
        }

        switch (type){
            case "info":
                String id = request.getParameter("id");
                if(id != null && !id.isEmpty()){
                    request.setAttribute("orderInfo", orderService.getOrderInfo(Integer.parseInt(id)));
                }
                break;
            case "pending":
                request.setAttribute("subList", orderService.getPendingSubs(Integer.parseInt(from)));
                break;
            case "expired":
                request.setAttribute("subList", orderService.getExpiredOrders(Integer.parseInt(from)));

        }
        request.setAttribute("type", type);
        return Path.LIBRARIAN_PAGE;
    }
}
