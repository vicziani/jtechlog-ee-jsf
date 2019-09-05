package empapp;

import lombok.Data;

@Data
public class EmployeeDto {

    private long id;

    private String name;

    private int salary;

    public EmployeeDto(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        salary = employee.getSalary();
    }


}
