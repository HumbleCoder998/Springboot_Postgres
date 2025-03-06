package com.capgemini.employees.Repositories;
import com.capgemini.employees.Models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
     Optional<Department> findByDepartment(String department);
}
