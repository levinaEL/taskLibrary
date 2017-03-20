package com.epam.library.utils;

import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class PrintHelper {
    private static Logger logger = Logger.getLogger(PrintHelper.class);

    public static void showEmployeesWithMore(Map<Employee, Integer> map){
        for (Map.Entry<Employee, Integer> entry : map.entrySet()) {
            logger.info(entry.getKey().getName() + " : " + entry.getValue());
        }
    }
    public static void showEmployeesLess(Map<Employee, Integer> map) {
        for (Map.Entry<Employee, Integer> entry : map.entrySet()) {
            Employee employee = entry.getKey();
            logger.info(employee.getName() + "," + employee.getBirthday() + " : " + entry.getValue());
        }
    }

    public static void showBooks(List<Book> bookList) {
        for (Book book: bookList) {
            logger.info(book);
        }
    }
}
