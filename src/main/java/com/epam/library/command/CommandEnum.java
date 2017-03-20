package com.epam.library.command;

import com.epam.library.command.impl.*;

public enum CommandEnum {

    RENAME{
        {
            command = new RenameBookCommand();
        }
    },
    GET{
        {
            command = new GetBookCommand();
        }
    },
    FIND_EMPLOYEE_WITH_MORE{
        {
            command = new GetEmployeeWithMoreThenOneCommand();
        }
    },
    FIND_EMPLOYEE_WITH_LESS{
        {
            command = new GetEmployeeWithLessThenTwoCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
