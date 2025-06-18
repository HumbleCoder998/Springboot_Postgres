package com.capgemini.employees.Controller;

import com.capgemini.employees.DTO.EmployeeRequest;
import com.capgemini.employees.DTO.EmployeeResponse;
import com.capgemini.employees.Exceptions.EmployeeException;
import com.capgemini.employees.Models.Employee;
import com.capgemini.employees.Service.EmployeeService;
import com.capgemini.employees.Utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.net.http.HttpClient;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
public class EmployeesController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    Validation validation;

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
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest)
    {
     Employee newEmployee = employeeRequest.from(employeeRequest);
     validation.validateAndUpdate(newEmployee,employeeRequest);
     return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(newEmployee));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable UUID id)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployee(id).toEmployeeResponse());
        }
        catch(NoSuchElementException ex)
        {
            return ResponseEntity.internalServerError().body(new String("No employee exist with provided id"));
        }
    }
}
