package pro.sky.java.course2.mockitohwtest.model;

import java.util.Objects;

public class Employee {
    private final String lastName;
    private final String firstName;

    private final String departmentId;
    private final Integer salaryOfEmployee;


    public Employee(String lastName, String firstName, String departmentId, Integer salaryOfEmployee) {
        this.firstName = lastName;
        this.lastName = firstName;
        this.departmentId = departmentId;
        this.salaryOfEmployee = salaryOfEmployee;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public int getSalaryOfEmployee() {
        return salaryOfEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employees = (Employee) o;
        return Objects.equals(getFirstName(), employees.getFirstName()) && Objects.equals(getLastName(), employees.getLastName()) && Objects.equals(getDepartmentId(), employees.getDepartmentId()) && Objects.equals(getSalaryOfEmployee(), employees.getSalaryOfEmployee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getDepartmentId(), getSalaryOfEmployee());
    }

    @Override
    public String toString() {
        return "Имя сотрудника: " + firstName + ", "
                + "фамилия сотрудника: " + lastName + ", " + " Отдел: " + departmentId + ", " + " Заработанная плата: "
                + salaryOfEmployee;

    }
}
