package com.arena.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsFilter {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "HR", 50000, 30),
                new Employee(2, "Bob", "Engineering", 70000, 25),
                new Employee(3, "Charlie", "Engineering", 90000, 35),
                new Employee(4, "David", "Marketing", 60000, 28),
                new Employee(5, "Eve", "HR", 45000, 32),
                new Employee(6, "Frank", "Marketing", 80000, 40),
                new Employee(7, "Grace", "Engineering", 120000, 45)
        );

        //salary > 60,000
        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getSalary() > 60_000)
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
        System.out.println("\nEmp having salary > 60,000"+filteredEmployees);

        //only names of engineers
        List<String> engineerEmployees = employees.stream()
                .filter(e -> "Engineering".equalsIgnoreCase(e.getDepartment()))
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println("\nEngineers"+engineerEmployees);

        //average salary
        double avgSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
        System.out.println("\nAverage Salary: "+avgSalary);

        //find employee with the highest salary
        Employee wellPaidEmployee = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .orElse(null);
        System.out.println("\nHighest Paid Employee: "+wellPaidEmployee.getName() +" ($"+wellPaidEmployee.getSalary()+")");

        //grouping employees by action
        Map<String, List<Employee>> groupedEmployees = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("\nGrouped Employees: "+groupedEmployees);

        //total salary
        Double totalSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(0.0, (s1, s2) -> s1 + s2);
        System.out.println("\nTotal Salary: "+totalSalary);


    }
}
