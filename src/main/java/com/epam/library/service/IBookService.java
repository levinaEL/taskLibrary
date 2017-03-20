package com.epam.library.service;

import com.epam.library.dao.exceptions.DaoException;
import com.epam.library.domain.Book;
import com.epam.library.service.exceptions.ServiceException;

import java.util.List;

public interface IBookService {
    void addBook(Book book) throws ServiceException;
    List<Book> getBookByTitle (String title) throws ServiceException;
    Book getBookById (long id) throws ServiceException;
    void updateBook(Book book) throws ServiceException;
    void renameBookTitle(String oldTitle, String newTitle) throws ServiceException;
    void deleteBook(String title) throws ServiceException;
}
