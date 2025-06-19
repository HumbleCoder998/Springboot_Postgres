package com.capgemini.employees.Service;

import com.capgemini.employees.DTO.EmployeeRequest;
import com.capgemini.employees.DTO.EmployeeResponse;
import com.capgemini.employees.Exceptions.EmployeeException;
import com.capgemini.employees.Exceptions.EmployeeNotFoundException;
import com.capgemini.employees.Models.Employee;
import com.capgemini.employees.Repositories.EmployeeRepository;
import com.capgemini.employees.Utils.Validation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    Validation validation;

    public Page<Employee> getAllEmployees(int page , int size) throws EmployeeException {
        if(employeeRepository.findAll().size() >= 1) {
            Pageable pageable = PageRequest.of(page,size);
            return employeeRepository.findAll(pageable);
        }
        throw new EmployeeException("Employees not exist");
    }

    public EmployeeResponse saveEmployee(Employee newEmployee) {
        Employee employeeSaved = employeeRepository.save(newEmployee);
        return employeeSaved.toEmployeeResponse();
    }

    public Employee findEmployee(UUID id) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElseThrow(EmployeeNotFoundException::new);
    }

    public void deleteEmployee(UUID id) throws EmployeeNotFoundException {
            findEmployee(id);
            employeeRepository.deleteById(id);
    }

    public EmployeeResponse updateEmployee(Employee existingEmployee, EmployeeRequest employeeRequest) {
        BeanUtils.copyProperties(employeeRequest,existingEmployee , validation.getNullPropertyNames(employeeRequest));
        validation.validateAndUpdate(existingEmployee,employeeRequest);
        return employeeRepository.save(existingEmployee).toEmployeeResponse();
    }
}
