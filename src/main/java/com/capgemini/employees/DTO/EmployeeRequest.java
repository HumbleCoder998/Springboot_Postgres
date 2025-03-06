package com.capgemini.employees.DTO;

import com.capgemini.employees.Models.Address;
import com.capgemini.employees.Models.Department;
import com.capgemini.employees.Models.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private String name;
    private int age;
    private DepartmentRequest departmentRequest;
    private AddressRequest addressRequest;

    public Employee from(EmployeeRequest employeeRequest)
    {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest,employee);
        Department department = new Department();
        department.setDepartment(employeeRequest.departmentRequest.getDepartment());
        employee.setDepartment(department);
        Address address = new Address();
        address.setLocation(employeeRequest.addressRequest.getLocation());
        employee.setAddress(address);
        return employee;
    }

}
