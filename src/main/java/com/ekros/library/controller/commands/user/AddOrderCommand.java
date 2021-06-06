package com.ekros.library.controller.commands.user;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.Status;
import com.ekros.library.model.entity.Order;
import com.ekros.library.model.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.SQLException;

public class AddOrderCommand implements ICommand {

    private final OrderService orderService;

    private final Logger log = Logger.getLogger(AddOrderCommand.class);

    public AddOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String userId = request.getParameter("userId");
        String bookId = request.getParameter("bookId");
        Date term = Date.valueOf(request.getParameter("term"));

        if(userId == null || bookId == null || userId.isEmpty() || bookId.isEmpty() || term == null){
            CommandUtils.setMessage(request, "Wrong request!");
            return Path.ERROR_PAGE;
        }
        try {
            orderService.addOrder(new Order(Integer.parseInt(userId),
                    Integer.parseInt(bookId),
                    term, null, 0L, Status.PENDING));
        }catch (SQLException e){
            log.error(e.getMessage());
            CommandUtils.setMessage(request, "You already have an active order for this book.");
            return Path.MAIN_PAGE;
        }
        return Path.REDIRECT_PROFILE;
    }
}
