package com.ekros.library.controller.commands.librarian;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;

public class AddFineCommand implements ICommand {

    private final OrderService orderService;

    public AddFineCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String fine = request.getParameter("fine");

        if(!CommandUtils.validateId(id) && !CommandUtils.validateId(fine)){
            CommandUtils.setMessage(request, "Invalid id or fine value!");
            return Path.LIBRARIAN_PAGE;
        }

        orderService.addFine(Integer.parseInt(id), Integer.parseInt(fine));

        return Path.REDIRECT_LIBRARIAN_PAGE;
    }
}
