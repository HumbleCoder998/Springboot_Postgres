package com.capgemini.employees.Exceptions;

public class EmployeeException extends Exception{
    String message;

    public EmployeeException(String message) {
        super(message);
    }
}
