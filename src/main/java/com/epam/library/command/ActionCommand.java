package com.epam.library.command;

import com.epam.library.domain.Request;

public interface ActionCommand {
    void execute(Request request);
}
