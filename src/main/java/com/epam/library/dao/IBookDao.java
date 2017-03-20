package com.epam.library.dao;

import com.epam.library.dao.exceptions.DaoException;
import com.epam.library.domain.Book;

import java.util.List;

public interface IBookDao {

    void createOrUpdate(Book book) throws DaoException;
    Book getBookById(long id) throws DaoException;
    List<Book> getBookByTitle (String title) throws DaoException;
    void deleteBook(String title) throws DaoException;

}
