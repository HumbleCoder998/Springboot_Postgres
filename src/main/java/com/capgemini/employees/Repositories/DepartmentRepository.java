package com.capgemini.employees.Repositories;
import com.capgemini.employees.Models.Department;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
     Optional<Department> findByDepartment(String department);

     @Query("Select d.id , Length(d.department) as de from Department d where d.department like ?1%")
     Optional<List<Department>> findByDepartmentName(String department , Sort sort);
}
