package pro.sky.java.course2.mockitohwtest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.mockitohw.model.Employee;
import pro.sky.java.course2.mockitohwtest.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.mockitohwtest.exception.EmployeeStorageIsFullException;
import pro.sky.java.course2.mockitohwtest.model.Employee;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidatorService());

    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest1(String name,
                                 String surname,
                                 int department,
                                 double salary) {
        Employee expected = new Employee(name, surname, department, salary);
        assertThat(employeeService.add(name, surname, department, salary)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(name, surname, department, salary));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void addNegativeTest2(String name,
                                 String surname,
                                 int department,
                                 double salary) {
        List<Employee> employees = generateEmployees(10);
        employees.forEach(employee ->
                assertThat(employeeService.add(employee.getName(), employee.getSurname(), employee.getDepartment(), employee.getSalary())).isEqualTo(employee)
        );

        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add(name, surname, department, salary));
    }

    @Test
    public void addNegativeTest3() {
        assertThatExceptionOfType(IncorrectNameException.class)
                .isThrownBy(() -> employeeService.add("Иван#", "Ivanov", 1, 55_000));

        assertThatExceptionOfType(IncorrectSurnameException.class)
                .isThrownBy(() -> employeeService.add("Petr", "!Петров", 1, 65_000));

        assertThatExceptionOfType(IncorrectNameException.class)
                .isThrownBy(() -> employeeService.add(null, "Иванова", 2, 75_000));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removeNegativeTest(String name,
                                   String surname,
                                   int department,
                                   double salary) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("test", "test"));

        Employee expected = new Employee(name, surname, department, salary);
        assertThat(employeeService.add(name, surname, department, salary)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removePositiveTest(String name,
                                   String surname,
                                   int department,
                                   double salary) {
        Employee expected = new Employee(name, surname, department, salary);
        assertThat(employeeService.add(name, surname, department, salary)).isEqualTo(expected);

        assertThat(employeeService.remove(name, surname)).isEqualTo(expected);
        assertThat(employeeService.getAll()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("params")
    public void findNegativeTest(String name,
                                 String surname,
                                 int department,
                                 double salary) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("test", "test"));

        Employee expected = new Employee(name, surname, department, salary);
        assertThat(employeeService.add(name, surname, department, salary)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void findPositiveTest(String name,
                                 String surname,
                                 int department,
                                 double salary) {
        Employee expected = new Employee(name, surname, department, salary);
        assertThat(employeeService.add(name, surname, department, salary)).isEqualTo(expected);

        assertThat(employeeService.find(name, surname)).isEqualTo(expected);
        assertThat(employeeService.getAll()).hasSize(1);
    }

    private List<Employee> generateEmployees(int size) {
        return Stream.iterate(1, i -> i + 1)
                .limit(size)
                .map(i -> new Employee("Name" + (char) ((int) 'a' + i), "Surname" + (char) ((int) 'a' + i), i, 10_000 + i))
                .collect(Collectors.toList());
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 1, 55_000),
                Arguments.of("Petr", "Petrov", 1, 65_000),
                Arguments.of("Mariya", "Ivanova", 2, 75_000)
        );
    }

}