package com.capgemini.employees.Models;

import com.capgemini.employees.DTO.AddressResponse;
import com.capgemini.employees.DTO.DepartmentResponse;
import com.capgemini.employees.DTO.EmployeeResponse;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.UUID;
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column()
    private String name;
    @ManyToOne()
    @JoinColumn(name ="address_id")
    private Address address;
    @Column
    private int age;
    @ManyToOne()
    @JoinColumn(name ="department_id")
    private Department department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeResponse toEmployeeResponse() {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(this,employeeResponse);
        DepartmentResponse departmentResponse = new DepartmentResponse();
        BeanUtils.copyProperties(this.department,departmentResponse);
        employeeResponse.setDepartmentResponse(departmentResponse);
        AddressResponse addressResponse = new AddressResponse();
        BeanUtils.copyProperties(this.address,addressResponse);
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }
}
