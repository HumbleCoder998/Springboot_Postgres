package com.capgemini.employees.Utils;

import com.capgemini.employees.DTO.EmployeeRequest;
import com.capgemini.employees.Models.Address;
import com.capgemini.employees.Models.Department;
import com.capgemini.employees.Models.Employee;
import com.capgemini.employees.Repositories.AddressRepository;
import com.capgemini.employees.Repositories.DepartmentRepository;
import com.capgemini.employees.Repositories.EmployeeRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Optional;

@Service
public class Validation {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    public Employee validateAndUpdate(Employee employee, EmployeeRequest employeeRequest) {
        Optional<Department> departmentOptional = departmentRepository.findByDepartment(employeeRequest.getDepartmentRequest().getDepartment());
        if (!departmentOptional.isPresent()) {
            Department department = new Department();
            department.setDepartment(employeeRequest.getDepartmentRequest().getDepartment());
            departmentRepository.save(department);
            employee.setDepartment(department);
        }
        else {
            employee.setDepartment(departmentOptional.get());
        }
        Optional<Address> optionalAddress = addressRepository.findByLocation(employeeRequest.getAddressRequest().getLocation());
        if (!optionalAddress.isPresent()) {
            Address address = new Address();
            address.setLocation(employeeRequest.getAddressRequest().getLocation());
            addressRepository.save(address);
            employee.setAddress(address);
        }
        else {
            employee.setAddress(optionalAddress.get());
        }
        return employee;
    }

    public String[] getNullPropertyNames(EmployeeRequest employeeRequest) {
            BeanWrapper beanWrapper = new BeanWrapperImpl(employeeRequest);
            return Arrays.stream(beanWrapper.getPropertyDescriptors())
                    .filter(pd -> beanWrapper.getPropertyValue(pd.getName()) == null)
                    .map(PropertyDescriptor::getName)
                    .toArray(String[]::new);
        }

    }

