package com.capgemini.employees.DTO;

public class EmployeeResponse {
    private String name;
    private int age;
    private DepartmentResponse departmentResponse;
    private AddressResponse addressResponse;

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

    public DepartmentResponse getDepartmentResponse() {
        return departmentResponse;
    }

    public void setDepartmentResponse(DepartmentResponse departmentResponse) {
        this.departmentResponse = departmentResponse;
    }

    public AddressResponse getAddressResponse() {
        return addressResponse;
    }

    public void setAddressResponse(AddressResponse addressResponse) {
        this.addressResponse = addressResponse;
    }
}
