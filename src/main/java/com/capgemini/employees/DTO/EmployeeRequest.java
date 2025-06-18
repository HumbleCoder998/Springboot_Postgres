package com.capgemini.employees.DTO;

import com.capgemini.employees.Models.Employee;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRequest {

    private String name;
    private int age;

    private long adhaarNumber;

    private DepartmentRequest departmentRequest;
    private AddressRequest addressRequest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getAdhaarNumber() {
        return adhaarNumber;
    }

    public void setAdhaarNumber(long adhaarNumber) {
        this.adhaarNumber = adhaarNumber;
    }

    public DepartmentRequest getDepartmentRequest() {
        return departmentRequest;
    }

    public void setDepartmentRequest(DepartmentRequest departmentRequest) {
        this.departmentRequest = departmentRequest;
    }

    public AddressRequest getAddressRequest() {
        return addressRequest;
    }

    public void setAddressRequest(AddressRequest addressRequest) {
        this.addressRequest = addressRequest;
    }

    public Employee from(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);
        return employee;
    }

}
