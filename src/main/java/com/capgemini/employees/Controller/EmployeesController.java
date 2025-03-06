package com.capgemini.employees.Controller;

import com.capgemini.employees.DTO.EmployeeRequest;
import com.capgemini.employees.EmployeesApplication;
import com.capgemini.employees.Exceptions.EmployeeException;
import com.capgemini.employees.Models.Employee;
import com.capgemini.employees.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeesController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<?> getEmployees()
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
        }catch(EmployeeException ex)
        {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest employeeRequest)
    {
     Employee newEmployee = employeeRequest.from(employeeRequest);
     return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(newEmployee));
    }
}
