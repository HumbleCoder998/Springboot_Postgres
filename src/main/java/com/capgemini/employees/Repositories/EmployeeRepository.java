package com.capgemini.employees.Repositories;

import com.capgemini.employees.Models.Department;
import com.capgemini.employees.Models.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<List<Employee>> findByDepartment_Id(Integer id);
    Optional<List<Employee>> findBySalaryBetween(long salary , long salary2);
    Optional<List<Employee>> findBySalaryGreaterThan(long salary);
    Optional<List<Employee>> findByNameLike(String name);
    Optional<List<Employee>> findByAgeNotNull();
    Optional<List<Employee>> findByNameStartingWith(String name);
    Optional<List<Employee>> findByNameAndNationality(String name , String nationality);
    Optional<List<Employee>> findByNameOrAge(String name , int age);
    Optional<List<Employee>> findByAgeIn(List<Integer> ages);
    Optional<List<Employee>> findByNameNot(String name);
    Optional<List<Employee>> findByNameNotLike(String name);
    Optional<List<Employee>> findByNameContaining(String name);
    Optional<List<Employee>> findByAgeOrderByNameDesc(Integer age);
    Optional<List<Employee>> findByName(String name);
    @Query("Select e from Employee e Where e.salary = ?1")
    Optional<List<Employee>> findEmployeeBySalary(long salary , Sort sort);

    @NativeQuery("SELECT e from employee e JOIN department d ON e.department_id = d.id WHERE d.department = ?1")
    Optional<List<Employee>> findEmployeesByDepartment(String department);





}
