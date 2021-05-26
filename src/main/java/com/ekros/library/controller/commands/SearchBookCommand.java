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
        Integer from = (Integer) request.getAttribute("from");

        if(name == null){
            name = "";
            request.setAttribute("books", bookService.getBooksByContainName(name, 0));
        }else if(name.length() < 5){
            request.setAttribute("searchMessage", "The title is too short!");
        }else if(from == null){
            request.setAttribute("message", "Field 'form' not found!");
            return "/error";
        }else{
            request.setAttribute("books", bookService.getBooksByContainName(name, from));
        }

        int count = bookService.getCount(name);
        request.setAttribute("count", count);
        request.setAttribute("pages", 1 + count/20);
        return "/";
    }
}
