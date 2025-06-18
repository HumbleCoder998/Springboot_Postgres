package com.capgemini.employees.Exceptions;

public class EmployeeNotFoundException extends Exception {
    private String message;

    public EmployeeNotFoundException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
