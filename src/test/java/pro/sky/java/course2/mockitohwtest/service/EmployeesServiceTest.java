package pro.sky.java.course2.mockitohwtest.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.mockitohw.model.Employee;
import pro.sky.java.course2.mockitohwtest.model.Employee;

import java.util.stream.Stream;

class EmployeesServiceTest {
    EmployeesService service = new EmployeesService();
    @ParameterizedTest
    @MethodSource("provideStringsForisBlank")
    void addEmployee(Employee source) {
        System.out.printf("Добавляем нового сотрудника %s",source);
        var employee = service.addEmployee(source);


    }

    private static Stream<Arguments> provideStringsForisBlank() {
        return Stream.of(
                Arguments.of(new Employee("lastname","firstname","1",400_000)),
                Arguments.of(new Employee("lastname","firstname","1",400_000)),
                Arguments.of(new Employee("lastname","firstname","1",400_000))
        );
    }

}