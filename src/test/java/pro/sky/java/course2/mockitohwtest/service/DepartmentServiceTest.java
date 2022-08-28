package pro.sky.java.course2.mockitohwtest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    DepartmentService service;

    DepartmentService departmentService;

    @BeforeEach
     void init() {
        var EmployeesService = Mockito.mock(EmployeesService.class);
        service = new DepartmentService(EmployeesService);
    }
}