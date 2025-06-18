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
    private String id;
    @Column()
<<<<<<< HEAD
    private String name;
    @Column
    private long salary;
    @Column
    private String nationality;
    @Column
    private int experience;
=======
    private String first_name;
    @Column
    private String last_name;
>>>>>>> e304d56d103e82c30a2ab3184c6093cd1ed04f63
    @ManyToOne()
    @JoinColumn(name ="address_id")
    private Address address;
    @Column
    private int age;
    @Column(unique = true , length = 12 , nullable = false)
    private long adhaarNumber;

    @ManyToOne()
    @JoinColumn(name ="department_id")
    private Department department;


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

<<<<<<< HEAD
    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
=======
    public long getAdhaarNumber() {
        return adhaarNumber;
    }

    public void setAdhaarNumber(long adhaarNumber) {
        this.adhaarNumber = adhaarNumber;
    }

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
>>>>>>> e304d56d103e82c30a2ab3184c6093cd1ed04f63
    }

    public EmployeeResponse toEmployeeResponse() {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(this,employeeResponse);
        DepartmentResponse departmentResponse = new DepartmentResponse();
        BeanUtils.copyProperties(this.department,departmentResponse);
        employeeResponse.setDepartmentResponse(departmentResponse);
        AddressResponse addressResponse = new AddressResponse();
        BeanUtils.copyProperties(this.getAddress(),addressResponse);
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }
}
