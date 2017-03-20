package com.epam.library.service.impl;

import com.epam.library.dao.IBookDao;
import com.epam.library.dao.exceptions.DaoException;
import com.epam.library.dao.impl.BookDao;
import com.epam.library.domain.Book;
import com.epam.library.service.IBookService;
import com.epam.library.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class BookService implements IBookService{
    private static Logger logger = Logger.getLogger(BookService.class);
    private IBookDao bookDao;

    public BookService() {
        try {
            bookDao = BookDao.getInstance();
        } catch (Exception e) {
            logger.error("Exception in getting bookDao instance", e);
        }
    }

    public void addBook(Book book) throws ServiceException {
        try {
            bookDao.createOrUpdate(book);
        } catch (DaoException e) {
            throw new ServiceException("Service exception in adding new book", e);
        }
    }

    public List<Book> getBookByTitle(String title) throws ServiceException {
        List<Book> books;
        try {
            books = bookDao.getBookByTitle(title);
        } catch (DaoException e) {
            throw new ServiceException("Service exception in getting book by title", e);
        }
        return books;
    }

    public Book getBookById(long id) throws ServiceException {
       Book book;
        try {
            book = bookDao.getBookById(id);
        } catch (DaoException e) {
            throw new ServiceException("Service exception in getting book by id", e);
        }
        return book;
    }

    public void updateBook(Book book) throws ServiceException {
        try {
            bookDao.createOrUpdate(book);
        } catch (DaoException e) {
            throw new ServiceException("Service exception in updating book", e);
        }
    }

    public void renameBookTitle(String oldTitle, String newTitle) throws ServiceException {
        List<Book> books = getBookByTitle(oldTitle);
        for(Book book : books){
            book.setTitle(newTitle);
            updateBook(book);
        }
    }

    public void deleteBook(String title) throws ServiceException {
        try {
            bookDao.deleteBook(title);
        } catch (DaoException e) {
            throw new ServiceException("Service exception in deleting book", e);
        }
    }
}
