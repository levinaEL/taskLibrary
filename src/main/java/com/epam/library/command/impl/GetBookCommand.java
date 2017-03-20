package com.epam.library.command.impl;

import com.epam.library.command.ActionCommand;
import com.epam.library.domain.Request;
import com.epam.library.service.IBookService;
import com.epam.library.service.exceptions.ServiceException;
import com.epam.library.service.impl.BookService;
import org.apache.log4j.Logger;

import static com.epam.library.utils.PrintHelper.showBooks;

public class GetBookCommand implements ActionCommand {
    private static Logger logger = Logger.getLogger(GetBookCommand.class);
    private static final String TITLE = "title";

    public void execute(Request request) {
        String title = (String) request.getParameter(TITLE);
        IBookService bookService = new BookService();
        try {
            showBooks(bookService.getBookByTitle(title));
        } catch (ServiceException e) {
            logger.error("Failed get command", e);
        }
    }
}
