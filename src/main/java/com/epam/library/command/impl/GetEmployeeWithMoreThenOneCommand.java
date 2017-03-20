package com.epam.library.command.impl;

import com.epam.library.command.ActionCommand;
import com.epam.library.domain.Request;
import com.epam.library.service.IEmployeeService;
import com.epam.library.service.exceptions.ServiceException;
import com.epam.library.service.impl.EmployeeService;
import com.epam.library.utils.PrintHelper;
import org.apache.log4j.Logger;

import static com.epam.library.utils.PrintHelper.showEmployeesWithMore;

public class GetEmployeeWithMoreThenOneCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(GetEmployeeWithMoreThenOneCommand.class);

    public void execute(Request request) {
        IEmployeeService employeeService = new EmployeeService();
        try {
            showEmployeesWithMore(employeeService.listEmployeeWithMoreThenOne());
        } catch (ServiceException e) {
            logger.error("Failed to execute command get employees with more then one", e);
        }
    }
}
