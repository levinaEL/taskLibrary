package com.epam.library.main;

import com.epam.library.controller.Controller;
import com.epam.library.domain.Request;
import org.apache.log4j.Logger;
import java.util.Scanner;

public class Run {
    private static Logger logger = Logger.getLogger(Run.class);
    private static final String EXIT = "EXIT";
    private static final String GET = "GET";
    private static final String RENAME = "RENAME";

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        String command;
        Request request = new Request();
        Controller controller = new Controller();

        try {
            while (true) {
                System.out.println("Please, choose the command: \n" +
                        "GET - for getting book by title \n" +
                        "RENAME - for renaming book title \n" +
                        "FIND_EMPLOYEE_WITH_MORE - for finding all employees with more than one books \n" +
                        "FIND_EMPLOYEE_WITH_MORE - for finding all employees with less than two books \n" +
                        "EXIT - for exit the program");
                command = scanner.nextLine();
                if (command.equalsIgnoreCase(EXIT)) {
                    break;
                }
                if (command.equalsIgnoreCase(GET)) {
                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();
                    request.setParameter("title", title);
                }
                if (command.equalsIgnoreCase(RENAME)) {
                    System.out.println("Enter old title: ");
                    String oldTitle = scanner.nextLine();
                    System.out.println("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    request.setParameter("old", oldTitle);
                    request.setParameter("new", newTitle);
                }
                controller.process(command, request);

            }
        } catch (RuntimeException e) {
            logger.error("Invalid command!", e);
        }
    }


}

