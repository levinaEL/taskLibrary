package com.epam.library.service.impl;

import com.epam.library.dao.IBookDao;
import com.epam.library.dao.IEmployeeDao;
import com.epam.library.dao.exceptions.DaoException;
import com.epam.library.dao.impl.BookDao;
import com.epam.library.dao.impl.EmployeeDao;
import com.epam.library.domain.Employee;
import com.epam.library.service.IEmployeeService;
import com.epam.library.service.exceptions.ServiceException;
import org.apache.log4j.Logger;

import java.util.Map;

public class EmployeeService implements IEmployeeService {
    private static Logger logger = Logger.getLogger(EmployeeService.class);
    private IEmployeeDao employeeDao;

    public EmployeeService() {
        try {
            employeeDao = EmployeeDao.getInstance();
        } catch (Exception e) {
            logger.error("Exception in getting employeeDao instance", e);
        }
    }

    public Map<Employee, Integer> listEmployeeWithMoreThenOne() throws ServiceException {
        Map<Employee, Integer> map = null;
        try {
            map = employeeDao.listEmployeeWithMoreThenOne();
        } catch (DaoException e) {
            throw new ServiceException("Service exception in getting report about employees with more than one book", e);
        }
        return map;
    }

    public Map<Employee, Integer> listEmployeeWithLessThenTwo() throws ServiceException {
        Map<Employee, Integer> map = null;
        try {
            map = employeeDao.listEmployeeWithLessThenTwo();
        } catch (DaoException e) {
            throw new ServiceException("Service exception in getting report about employees with less than two book", e);
        }
        return map;
    }
}
