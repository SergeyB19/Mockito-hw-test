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
public class EmployeesService {


    List<Employee> employees = new ArrayList<>(List.of(

            new Employee("Иванов", "Иван", "1", 105_000),
            new Employee("Иванов", "Евгений", "2", 100_000),
            new Employee("Сидоров", "Иван", "1", 84_000),
            new Employee("Зверев", "Михаил", "3", 300_000),
            new Employee("Клюева",  "Людмила", "5", 250_000),
            new Employee("Иванов", "Егор", "3", 150_000),
            new Employee("Кузнецов","Сергей", "3", 100_000),
            new Employee("Клоков", "Сергей", "4", 90_000),
            new Employee("Куркова", "Светлана", "2", 95_000),
            new Employee("Михалева", "Елена", "1", 100_000)
    ));

    public Employee addEmployee(Employee employee) {
        if (employees.contains(employee)){
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < 11){
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }
    public Employee removeEmployee(Employee employee) {
        if (!employees.contains(employee)){
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }
    public Employee findEmployee(Employee employee) {
        if (!employees.contains(employee)){
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
