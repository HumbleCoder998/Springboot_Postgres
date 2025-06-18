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
    public ResponseEntity<?> getEmployees() throws EmployeeException {
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest)
    {
     Employee newEmployee = employeeRequest.from(employeeRequest);
     validation.validateAndUpdate(newEmployee,employeeRequest);
     return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(newEmployee));
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable UUID id , @RequestBody EmployeeRequest employeeRequest) throws EmployeeNotFoundException {
        Employee existingEmployee = employeeService.findEmployee(id);
        return  ResponseEntity.status(HttpStatus.CREATED).body(employeeService.updateEmployee(existingEmployee,employeeRequest));


    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable UUID id) throws EmployeeNotFoundException {
    employeeService.deleteEmployee(id);
    return  ResponseEntity.status(HttpStatus.OK).body("Employee is deleted");
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable UUID id) throws EmployeeNotFoundException {
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.findEmployee(id).toEmployeeResponse());
    }
}
