package empapp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@NamedQuery(name = "Employee.findEmployees", query = "select e from Employee e order by e.name")
@NamedQuery(name = "Employee.countEmployeesWithName", query = "select count(employee.id) from Employee employee where employee.name = :name")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
}
