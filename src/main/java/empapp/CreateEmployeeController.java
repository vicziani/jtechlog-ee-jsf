package empapp;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Model
public class CreateEmployeeController {


    private EmployeeServiceBean employeeServiceBean;

    private MessageContext messageContext;

    private List<Integer> salaryOptions;

    private CreateEmployeeCommand command =
            new CreateEmployeeCommand("", 100_000);

    @Inject
    public CreateEmployeeController(EmployeeServiceBean employeeServiceBean, MessageContext messageContext) {
        this.employeeServiceBean = employeeServiceBean;
        this.messageContext = messageContext;
    }

    @PostConstruct
    public void initSalaryOptions() {
        salaryOptions = employeeServiceBean.listSalaryOptions();
    }

    public String createEmployee() {
        var count = employeeServiceBean.countEmployeesWithName(command.getName());
        if (count > 0) {
//            FacesContext.getCurrentInstance()
//                    .addMessage(null,
//                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name already exists!", ""));


            messageContext.addMessage("name_already_exists");
            return null;
        }

        employeeServiceBean.createEmployee(command);

        // FacesContext.getCurrentInstance() is null in

//        FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getFlash()
//                .setKeepMessages(true);
//
//        FacesContext.getCurrentInstance()
//                .addMessage(null,
//                        new FacesMessage("Employee has created with name "
//                                + command.getName()));
        messageContext.addFlashMessage("employee_has_created", command.getName());

        return "index.xhtml?faces-redirect=true";
    }

    public CreateEmployeeCommand getCommand() {
        return command;
    }

    public void setCommand(CreateEmployeeCommand command) {
        this.command = command;
    }

    public List<Integer> getSalaryOptions() {
        return salaryOptions;
    }


}
