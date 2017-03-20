package com.epam.library.command.impl;

import com.epam.library.command.ActionCommand;
import com.epam.library.domain.Request;
import com.epam.library.service.IBookService;
import com.epam.library.service.exceptions.ServiceException;
import com.epam.library.service.impl.BookService;
import org.apache.log4j.Logger;

public class RenameBookCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(RenameBookCommand.class);
    private static final String OLD_TITLE = "old";
    private static final String NEW_TITLE = "new";

    public void execute(Request request) {
        String oldTitle = (String)request.getParameter(OLD_TITLE);
        String newTitle = (String)request.getParameter(NEW_TITLE);
        IBookService bookService = new BookService();
        try {
            bookService.renameBookTitle(oldTitle, newTitle);
        } catch (ServiceException e) {
            logger.error("Failed command rename book", e);
        }

    }
}
