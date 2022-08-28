package pro.sky.java.course2.mockitohwtest.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.mockitohw.model.Employee;
import pro.sky.java.course2.mockitohwtest.exception.EmployeeNotFoundException;
import pro.sky.java.course2.mockitohwtest.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeesService;

    public DepartmentService(EmployeeService employeesService) {
        this.employeesService = employeesService;
    }

    public Employee employeeWithMaxSalary(int departmentId) {
        return employeesService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .max(Comparator.comparingDouble((Employee::getSalary))
                        .orElseThrow(EmployeeNotFoundException::new));
    }

    public Employee employeeWithMinSalary(int departmentId) {
        return employeesService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparingDouble((Employee::getSalary))
                        .orElseThrow(EmployeeNotFoundException::new));
    }

    public List<Employee> employeesFromDepartment(ind departmentId) {
        return employeesService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> employeesGroupeByDepartment() {
        return employeesService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
