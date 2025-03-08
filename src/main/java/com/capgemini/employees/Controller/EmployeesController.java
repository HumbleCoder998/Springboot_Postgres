package com.capgemini.employees.Controller;

import com.capgemini.employees.DTO.EmployeeRequest;
import com.capgemini.employees.DTO.EmployeeResponse;
import com.capgemini.employees.Exceptions.EmployeeException;
import com.capgemini.employees.Exceptions.EmployeeNotFoundException;
import com.capgemini.employees.Models.Employee;
import com.capgemini.employees.Service.EmployeeService;
import com.capgemini.employees.Utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable(name = "id") UUID id) throws EmployeeNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeDetails(id.toString()));
    }
}
