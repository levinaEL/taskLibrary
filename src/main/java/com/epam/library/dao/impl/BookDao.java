package com.epam.library.dao.impl;

import com.epam.library.dao.IBookDao;
import com.epam.library.dao.exceptions.DaoException;
import com.epam.library.dao.db.DBConnectionPool;
import com.epam.library.domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {

    private DBConnectionPool dbConnectionPool;

    private static final String BOOK_ID = "book_id";
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String BRIEF = "brief";
    private static final String PUBLISH_DATE = "publish_date";

    private final static String ADD_BOOK = "INSERT INTO BOOK (TITLE, AUTHOR, BRIEF, PUBLISH_DATE) VALUES (?, ?, ?, ?);";
    private final static String GET_BOOK_BY_TITLE = "SELECT * FROM BOOK WHERE TITLE = ?;";
    private final static String GET_BOOK_BY_ID = "SELECT * FROM BOOK WHERE BOOK_ID = ?;";
    private final static String DELETE_BOOK = "DELETE FROM BOOK WHERE TITLE = ?;";
    private final static String UPDATE_BOOK = "UPDATE BOOK SET TITLE = ?, AUTHOR = ?, BRIEF = ?, PUBLISH_DATE = ?" +
            " WHERE BOOK_ID = ?;";

    private BookDao() throws Exception {
        dbConnectionPool = DBConnectionPool.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private IBookDao instance;
        private Exception exception;

        Singleton() {
            try {
                instance = new BookDao();
            } catch (Exception e) {
                instance = null;
                exception = e;
            }
        }
    }

    public static IBookDao getInstance() throws Exception {
        if (Singleton.INSTANCE.instance == null) {
            throw Singleton.INSTANCE.exception;
        }
        return Singleton.INSTANCE.instance;
    }

    private Book extractBookFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();

        book.setBookId(rs.getLong(BOOK_ID));
        book.setTitle(rs.getString(TITLE));
        book.setAuthor(rs.getString(AUTHOR));
        book.setBrief(rs.getString(BRIEF));
        book.setPublishDate(rs.getDate(PUBLISH_DATE));

        return book;
    }

    private void prepareExecuteStatement(Book book, PreparedStatement preparedStatement) throws SQLException {

        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setString(3, book.getBrief());
        preparedStatement.setDate(4, (Date) book.getPublishDate());
        preparedStatement.setLong(5, book.getBookId());
    }

    public void createOrUpdate(Book book) throws DaoException {
        Connection connection = null;
        try {
            connection = dbConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK);
            prepareExecuteStatement(book, preparedStatement);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException("Dao exception in adding new book", e);
        } finally {
            if (connection != null) {
                dbConnectionPool.freeConnection(connection);
            }
        }
    }

    public Book getBookById(long id) throws DaoException {
        ResultSet rs;
        Book book = new Book();
        Connection connection = null;
        try {
            connection = dbConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOK_BY_ID);
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                book = extractBookFromResultSet(rs);
            }
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
            throw new DaoException("Dao exception in getting book by title", e);
        } finally {
            if (connection != null) {
                dbConnectionPool.freeConnection(connection);
            }
        }
        return book;
    }

    public List<Book> getBookByTitle(String title) throws DaoException {
        ResultSet rs;
        List<Book> bookList = new ArrayList<Book>();
        Connection connection = null;
        try {
            connection = dbConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOK_BY_TITLE);
            preparedStatement.setString(1, title);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                bookList.add(extractBookFromResultSet(rs));
            }
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
            throw new DaoException("Dao exception in getting book by title", e);
        } finally {
            if (connection != null) {
                dbConnectionPool.freeConnection(connection);
            }
        }
        return bookList;
    }

    public void deleteBook(String title) throws DaoException {
        Connection connection = null;
        try {
            connection = dbConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK);
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoException("Dao exception in deleting book", e);
        } finally {
            if (connection != null) {
                dbConnectionPool.freeConnection(connection);
            }
        }
    }
}
