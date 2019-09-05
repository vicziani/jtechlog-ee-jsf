package empapp;

import lombok.Data;

@Data
public class ModifyEmployeeCommand {

    private long id;

    private String name;

    private int salary;

}
