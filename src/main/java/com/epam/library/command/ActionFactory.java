package com.epam.library.command;

import org.apache.log4j.Logger;

public class ActionFactory {
    private static Logger logger = Logger.getLogger(ActionFactory.class);

    public static ActionCommand defineCommand(String action) {
        ActionCommand current = null;

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();

        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument exception in getting command ", e);
        }
        return current;
    }
}
