package com.ekros.library.controller.commands.admin;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.service.BookService;

import javax.servlet.http.HttpServletRequest;

public class DeleteBookCommand implements ICommand {
    private final BookService bookService;

    public DeleteBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        if(id == null){
            CommandUtils.setMessage(request, "Parameter id not found!");
            return Path.ERROR_PAGE;
        }
        bookService.deleteBook(Integer.parseInt(id));
        return CommandUtils.searchBookRequest(request);
    }
}
