package com.capgemini.employees.Service;

import com.capgemini.employees.DTO.EmployeeResponse;
import com.capgemini.employees.Exceptions.EmployeeException;
import com.capgemini.employees.Exceptions.EmployeeNotFoundException;
import com.capgemini.employees.Models.Employee;
import com.capgemini.employees.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() throws EmployeeException {
        if(employeeRepository.findAll().size() >= 1) {
            return employeeRepository.findAll();
        }
        throw new EmployeeException("Employees not exist");
    }

    public EmployeeResponse saveEmployee(Employee newEmployee) {
        Employee employeeSaved = employeeRepository.save(newEmployee);
        return employeeSaved.toEmployeeResponse();
    }

    public EmployeeResponse getEmployeeDetails(String id) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(!employeeOptional.isPresent())
        {
            throw new EmployeeNotFoundException("Employee doesn't exist");
        }
        return employeeOptional.get().toEmployeeResponse();
    }

    public Employee findEmployee(UUID id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElseThrow();
    }
}
