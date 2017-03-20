package com.epam.library.command.impl;

import com.epam.library.command.ActionCommand;
import com.epam.library.domain.Request;
import com.epam.library.service.IEmployeeService;
import com.epam.library.service.exceptions.ServiceException;
import com.epam.library.service.impl.EmployeeService;
import com.epam.library.utils.PrintHelper;
import org.apache.log4j.Logger;

import static com.epam.library.utils.PrintHelper.showEmployeesLess;

public class GetEmployeeWithLessThenTwoCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(GetEmployeeWithLessThenTwoCommand.class);

    public void execute(Request request) {
        IEmployeeService employeeService = new EmployeeService();
        try {
            showEmployeesLess(employeeService.listEmployeeWithLessThenTwo());
        } catch (ServiceException e) {
            logger.error("Failed to execute command get employees with less then two", e);
        }
    }
}
