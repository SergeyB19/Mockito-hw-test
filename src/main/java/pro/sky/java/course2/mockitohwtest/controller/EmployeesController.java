package pro.sky.java.course2.mockitohwtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.mockitohw.service.DepartmentService;
import pro.sky.java.course2.mockitohwtest.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private static DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/all")
    public String getEmployeesByDepartment(){
        return departmentService.getEmployeesByDepartment();
    }

    @GetMapping(value = "/all", params = "departmentId")
    public String getEmployeesInDepartment(@RequestParam("departmentId") String departmentId){
        return departmentService.getEmployeesInDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public String lowestSalaryInDepartment(@RequestParam ("departmentId") String departmentId){
        return departmentService.lowestSalaryInDepartment(departmentId);
    }

    @GetMapping("/max-salary")
    public String highestSalaryInDepartment(@RequestParam ("departmentId") String departmentId){
        return departmentService.highestSalaryInDepartment(departmentId);
    }
}
