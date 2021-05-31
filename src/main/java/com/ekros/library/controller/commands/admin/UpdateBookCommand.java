package com.ekros.library.controller.commands.admin;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.entity.Book;
import com.ekros.library.model.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UpdateBookCommand implements ICommand {

    private final BookService bookService;

    private final Logger log = Logger.getLogger(UpdateBookCommand.class);

    public UpdateBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String edition = request.getParameter("edition");
        String count = request.getParameter("count");
        String description = request.getParameter("description");
        String descriptionRu = request.getParameter("descriptionRu");

        log.info("---Edit book---");
        log.info("Id: " + id);
        log.info("bookName: " + name);
        log.info("Author: " + author);
        log.info("Edition: " + edition);
        log.info("Count: " + count);
        log.info("Desc: " + description);
        log.info("Desc ru: " + descriptionRu);

        if(id == null){
            CommandUtils.setMessage(request, "Parameter id not found!");
            return Path.ERROR_PAGE;
        }
        Book book = bookService.getBookById(Integer.parseInt(id));

        if(name != null){
            book.setName(name);
        }
        if(author != null){
            book.setAuthor(author);
        }
        if(edition != null){
            book.setEdition(edition);
        }
        if(count != null){
            book.setCount(Integer.parseInt(count));
        }
        if(description != null){
            book.setDescription(description);
        }
        if(descriptionRu != null){
            book.setDescriptionRu(descriptionRu);
        }
        bookService.updateBook(book);
        return CommandUtils.searchBookRequest(request);
    }
}
