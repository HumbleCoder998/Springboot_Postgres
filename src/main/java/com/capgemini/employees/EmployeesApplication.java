package com.capgemini.employees;

import com.capgemini.employees.Models.Address;
import com.capgemini.employees.Models.Department;
import com.capgemini.employees.Models.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);

		List<Employee> employeeList = new ArrayList<>();
		Employee employee = new Employee();
		employee.setAge(25);
		employee.setName("Maitreyi");
		employee.setSalary(1500000);

		Address address = new Address();
		address.setLocation("Jaipur");
		employee.setAddress(address);

		Department department = new Department();
		department.setDepartment("Development");
		employee.setDepartment(department);
		employeeList.add(employee);

		Employee employee2 = new Employee();
		employee2.setName("Priyanshu");
		employee2.setSalary(1200000);
		Address address2 = new Address();
		address2.setLocation("Ambala");
		employee2.setAddress(address2);
		employee2.setAge(28);
		employee2.setDepartment(department);
		employeeList.add(employee2);


		List<Employee> updatedList = employeeList.stream().sorted((emp,emp2) -> emp2.getAge()-emp.getAge()).collect(Collectors.toList());
		System.out.println("Employees sorted by age desc");
		updatedList.forEach(emp -> System.out.println(emp.getName()));
		updatedList = employeeList.stream().sorted((emp , emp2) -> emp.getName().compareTo(emp2.getName())).collect(Collectors.toList());
		System.out.println("Employees sorted by name asc");
		updatedList.forEach(emp -> System.out.println(emp.getName()));

		Employee optionalEmployee = employeeList.stream().max((emp1 , emp2) -> emp1.getAge()-emp2.getAge()).get();
		System.out.println("Max age between employees is " + optionalEmployee.getAge());
		System.out.println("Employees filtered by name first letter");
		employeeList.stream().filter(employee1 -> employee1.getName().startsWith("M") || employee1.getName().startsWith("P")).forEach(employee1 -> System.out.println(employee1.getName()));
		System.out.println("Employees sorted by address location desc");
		employeeList.stream().sorted((emp1,emp2) -> emp2.getAddress().getLocation().compareTo(emp1.getAddress().getLocation())).forEach(emp -> System.out.println(emp.getName()));
		System.out.println("Employees grouped by department");
		Map<Department, List<Employee>> empmap = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
		empmap.entrySet().forEach(System.out::println);
		System.out.println("Second largest salary employee");
		employeeList.stream().sorted((emp1,emp2) -> Math.toIntExact(emp2.getSalary() - emp1.getSalary())).skip(1).limit(1).forEach(emp -> System.out.println(emp.getName()));

	}

}
