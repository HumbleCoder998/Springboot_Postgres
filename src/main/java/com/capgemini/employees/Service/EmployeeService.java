package com.capgemini.employees.Service;

import com.capgemini.employees.DTO.EmployeeRequest;
import com.capgemini.employees.DTO.EmployeeResponse;
import com.capgemini.employees.Exceptions.EmployeeException;
import com.capgemini.employees.Exceptions.EmployeeNotFoundException;
import com.capgemini.employees.Models.Address;
import com.capgemini.employees.Models.Department;
import com.capgemini.employees.Models.Employee;
import com.capgemini.employees.Repositories.AddressRepository;
import com.capgemini.employees.Repositories.DepartmentRepository;
import com.capgemini.employees.Repositories.EmployeeRepository;
import com.capgemini.employees.Utils.Validation;
import io.micrometer.core.instrument.config.validate.Validated;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    Validation validation;

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
