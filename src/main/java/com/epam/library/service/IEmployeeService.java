package com.epam.library.service;

import com.epam.library.dao.exceptions.DaoException;
import com.epam.library.domain.Employee;
import com.epam.library.service.exceptions.ServiceException;

import java.util.Map;

public interface IEmployeeService {
    Map<Employee, Integer> listEmployeeWithMoreThenOne() throws ServiceException;
    Map<Employee, Integer> listEmployeeWithLessThenTwo() throws ServiceException;
}
