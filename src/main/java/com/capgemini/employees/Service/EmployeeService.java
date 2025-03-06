package com.capgemini.employees.Service;

import com.capgemini.employees.Exceptions.EmployeeException;
import com.capgemini.employees.Models.Employee;
import com.capgemini.employees.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() throws EmployeeException {
        if(employeeRepository.count() > 1)
            return employeeRepository.findAll();
        throw new EmployeeException("Employees not exist");
    }

    public Employee saveEmployee(Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }
}
