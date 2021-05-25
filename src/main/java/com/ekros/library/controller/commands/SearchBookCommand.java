package com.ekros.library.controller.commands;

import com.ekros.library.model.service.BookService;
import javax.servlet.http.HttpServletRequest;

public class SearchBookCommand implements ICommand{

    private final BookService bookService;

    public SearchBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String name = (String) request.getAttribute("bookName");
        if(name == null){
            request.setAttribute("message", "Book title not specified!");
            return "/error";
        }
        if(name.length() < 5){
            request.setAttribute("searchMessage", "The title is too short!");
            return "/";
        }

        request.setAttribute("books", bookService.getBooksByContainName(name));

        return "/";
    }
}
