package com.epam.library.dao.impl;

import com.epam.library.dao.IEmployeeDao;
import com.epam.library.dao.exceptions.DaoException;
import com.epam.library.dao.db.DBConnectionPool;
import com.epam.library.domain.Employee;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDao implements IEmployeeDao {
    private DBConnectionPool dbConnectionPool;

    private static final String ID = "employee_id";
    private final static String NAME = "name";
    private final static String EMAIL = "email";
    private final static String BOOK_COUNT = "book_count";
    private final static String BIRTHDAY = "birth_date";

    private final static String EMPLOYEES_MORE_THAN_ONE_BOOK = "SELECT e.*, count.book_count  " +
            "FROM EMPLOYEE as e " +
            "JOIN (SELECT emp_b.employee_id, COUNT(*) as book_count FROM BOOK_EMPLOYEE as emp_b " +
            "GROUP BY emp_b.employee_id HAVING book_count > 1) as count ON count.employee_id = e.employee_id" +
            " ORDER BY count.book_count; ";

    private final static String EMPLOYEES_LESS_THAN_TWO_BOOKS = "SELECT e.*, COUNT(emp_b.book_id) as book_count" +
            " FROM EMPLOYEE AS e  " +
            "LEFT JOIN BOOK_EMPLOYEE AS emp_b ON emp_b.employee_id = e.employee_id  " +
            "GROUP BY emp_b.employee_id , e.name, e.birth_date, e.email HAVING COUNT(*)<=2 " +
            "ORDER BY e.birth_date, book_count desc";

    private EmployeeDao() throws Exception {
        dbConnectionPool = DBConnectionPool.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private IEmployeeDao instance;
        private Exception exception;

        Singleton() {
            try {
                instance = new EmployeeDao();
            } catch (Exception e) {
                instance = null;
                exception = e;
            }
        }
    }

    public static IEmployeeDao getInstance() throws Exception {
        if (EmployeeDao.Singleton.INSTANCE.instance == null) {
            throw EmployeeDao.Singleton.INSTANCE.exception;
        }
        return EmployeeDao.Singleton.INSTANCE.instance;
    }

    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee employee = new Employee();

        employee.setEmployeeId(rs.getLong(ID));
        employee.setName(rs.getString(NAME));
        employee.setEmail(rs.getString(EMAIL));
        employee.setBirthday(rs.getDate(BIRTHDAY));

        return employee;
    }

    public Map<Employee, Integer> listEmployeeWithMoreThenOne() throws DaoException{
        Connection connection = null;
        Map<Employee, Integer> map = new HashMap<Employee, Integer>();
        try {
            connection = dbConnectionPool.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(EMPLOYEES_MORE_THAN_ONE_BOOK);
            while (rs.next()) {
                map.put(extractEmployeeFromResultSet(rs), rs.getInt(BOOK_COUNT));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DaoException("Dao exception in getting list of employees with more than one book", e);
        }  finally {

            if (connection != null) {
                dbConnectionPool.freeConnection(connection);
            }
        }

        return map;
    }

    public Map<Employee, Integer> listEmployeeWithLessThenTwo() throws DaoException{
        Connection connection = null;
        Map<Employee, Integer> map = new HashMap<Employee, Integer>();
        try {
            connection = dbConnectionPool.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(EMPLOYEES_LESS_THAN_TWO_BOOKS);
            while (rs.next()) {
                map.put(extractEmployeeFromResultSet(rs), rs.getInt(BOOK_COUNT));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new DaoException("Dao exception in getting list of employees with less than two book", e);
        }  finally {
            if (connection != null) {
                dbConnectionPool.freeConnection(connection);
            }
        }

        return map;
    }
}
