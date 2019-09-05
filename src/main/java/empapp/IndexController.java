package empapp;


import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model
public class IndexController {

    private EmployeeServiceBean employeeServiceBean;

    @Inject
    public IndexController(EmployeeServiceBean employeeServiceBean) {
        this.employeeServiceBean = employeeServiceBean;
    }

    public List<EmployeeDto> getEmployees() {
        return employeeServiceBean.listEmployees();
    }

    public String deleteEmployee(EmployeeDto employee) {
        var command = new DeleteEmployeeCommand();
        command.setId(employee.getId());
        employeeServiceBean.deleteEmployee(command);

        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Employee has deleted with name "
                        + employee.getName())
        );

        return "index.xhtml?faces-redirect=true";
    }
}
