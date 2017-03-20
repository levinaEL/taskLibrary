package com.epam.library.dao;

import com.epam.library.dao.exceptions.DaoException;
import com.epam.library.domain.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeDao {
    Map<Employee, Integer> listEmployeeWithMoreThenOne() throws DaoException;
    Map<Employee, Integer> listEmployeeWithLessThenTwo() throws DaoException;
}
