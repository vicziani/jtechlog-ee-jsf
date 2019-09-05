package empapp;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Model
public class EmployeeDetailsController {

    private EmployeeServiceBean employeeServiceBean;

    private long id;

    private ModifyEmployeeCommand command = new ModifyEmployeeCommand();

    @Inject
    public EmployeeDetailsController(EmployeeServiceBean employeeServiceBean) {
        this.employeeServiceBean = employeeServiceBean;
    }

    public void findEmployeeById() {
            var employee = employeeServiceBean.findEmployeeById(id);
            command = new ModifyEmployeeCommand();
            command.setId(employee.getId());
            command.setName(employee.getName());
            command.setSalary(employee.getSalary());
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public ModifyEmployeeCommand getCommand() {
        return command;
    }


    public String modifyEmployee() {
        employeeServiceBean.modifyEmployee(command);
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash()
                .put("successMessage", "Employess has modified with name " + command.getName());

        return "index.xhtml?faces-redirect=true";
    }

    public void setCommand(ModifyEmployeeCommand command) {
        this.command = command;
    }
}
