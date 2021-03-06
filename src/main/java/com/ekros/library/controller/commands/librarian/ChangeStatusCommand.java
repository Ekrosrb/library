package com.ekros.library.controller.commands.librarian;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.Status;
import com.ekros.library.model.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ChangeStatusCommand implements ICommand {

    private final OrderService orderService;

    public ChangeStatusCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String status = request.getParameter("status");

        if(!CommandUtils.validateId(id)){
            CommandUtils.setMessage(request, "Parameter Id not valid!");
            return Path.ERROR_PAGE;
        }

        orderService.updateStatus(Integer.parseInt(id), Status.valueOf(status));

        Map<String, String> param = new HashMap<>();
        param.put("id", id);
        param.put("type", request.getParameter("type"));
        return CommandUtils.addParamsToUrl(Path.REDIRECT_LIBRARIAN_PAGE, param);
    }
}
