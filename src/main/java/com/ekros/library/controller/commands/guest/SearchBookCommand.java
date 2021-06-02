package com.ekros.library.controller.commands.guest;

import com.ekros.library.controller.commands.CommandUtils;
import com.ekros.library.controller.commands.ICommand;
import com.ekros.library.controller.commands.Path;
import com.ekros.library.model.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SearchBookCommand implements ICommand {

    private final BookService bookService;
    private final Logger log = Logger.getLogger(SearchBookCommand.class);
    public SearchBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest request) throws Exception {
        String name = request.getParameter("bookName");
        String from = request.getParameter("from");

        log.info("Search: " + name);

        if(name == null || name.isEmpty()){
            name = "";
        }else if(name.length() < 5){
            CommandUtils.setMessage(request, "The title is too short!");
            return Path.INDEX_PAGE;
        }
        if(from == null){
            from = "0";
        }

        request.setAttribute("books", bookService.getBooksByContainName(name, Integer.parseInt(from)));

        int count = bookService.getCount(name);
        request.setAttribute("count", count);
        request.setAttribute("pages", CommandUtils.getPages(count));
        request.setAttribute("bookName", name);
        return Path.INDEX_PAGE;
    }
}
