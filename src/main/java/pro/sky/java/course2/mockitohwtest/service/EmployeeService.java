package pro.sky.java.course2.mockitohwtest.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.mockitohw.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.mockitohw.exception.EmployeeNotFoundException;
import pro.sky.java.course2.mockitohw.exception.EmployeeStorageIsFullException;
import pro.sky.java.course2.mockitohw.model.Employee;
import pro.sky.java.course2.mockitohwtest.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.mockitohwtest.exception.EmployeeNotFoundException;
import pro.sky.java.course2.mockitohwtest.exception.EmployeeStorageIsFullException;
import pro.sky.java.course2.mockitohwtest.model.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final ValidatorService validatorService;
    public EmployeeService(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    public Employee add(String name, String surname, int department, double salary) {
        Employee employee = new Employee(validatorService.validateName(name),
                validatorService.validateSurname(surname),
                department,
                salary
        );
        if (employee.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employee.size() < LIMIT) {
            employee.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee remove(String name, String surname) {
        Employee employee = employee.stream()
                .filter(emp -> emp.getName().equals(name) && emp.getSurname().equals(surname))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
        employees.remove(employee);
        return employee;
    }

    public Employee find(String name, String surname) {
        return employees.stream()
                .filter(emp -> emp.getName().equals(name) && emp.getSurname().equals(surname))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
