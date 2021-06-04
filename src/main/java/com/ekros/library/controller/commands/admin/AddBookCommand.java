package com.ekros.library.controller.commands.admin;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.Book;
import com.ekros.library.model.service.BookService;

import javax.servlet.http.HttpServletRequest;

public class AddBookCommand implements ICommand {

    private final BookService bookService;

    public AddBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String edition = request.getParameter("edition");
        String description = request.getParameter("description");
        String descriptionRu = request.getParameter("descriptionRu");
        String count = request.getParameter("count");

        if(name == null || name.isEmpty() || author == null || author.isEmpty() || edition == null || edition.isEmpty() || !CommandUtils.validateId(count) || description == null || descriptionRu == null){
            CommandUtils.setMessage(request, "Incorrect add book request!");
            return Path.MAIN_PAGE;
        }

        bookService.addBook(new Book(name, author, edition, description, descriptionRu, Integer.parseInt(count)));
        return Path.REDIRECT_MAIN_PAGE;
    }
}
