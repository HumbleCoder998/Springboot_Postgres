package com.capgemini.employees.DTO;

import com.capgemini.employees.Models.Employee;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRequest {

    private String first_name;
    private String last_name;
    private int age;

    private long adhaarNumber;

    private DepartmentRequest departmentRequest;
    private AddressRequest addressRequest;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
