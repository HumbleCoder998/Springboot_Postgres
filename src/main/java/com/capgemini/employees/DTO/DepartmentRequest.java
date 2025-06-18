package com.capgemini.employees.DTO;

import com.capgemini.employees.Models.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
    public String getDepartment() {
        return department;
    }

    private String department;

    public Department toDepartment() {
        Department department = new Department();
        department.setDepartment(this.getDepartment());
        return department;
    }
}
