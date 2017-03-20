package com.epam.library.controller;

import com.epam.library.command.ActionCommand;
import com.epam.library.command.ActionFactory;
import com.epam.library.domain.Request;


public class Controller {

    public void process(String commandName, Request request) {
        ActionCommand action = ActionFactory.defineCommand(commandName);
        action.execute(request);
    }
}
