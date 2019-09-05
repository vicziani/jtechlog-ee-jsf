package empapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class EmployeeServiceBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private EmployeeDaoBean employeeDaoBean;

    public List<EmployeeDto> listEmployees() {
        return employeeDaoBean.findEmployees()
                .stream()
                .map(EmployeeDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(command.getName(), command.getSalary());

        employeeDaoBean.saveEmployee(employee);
    }

    @Transactional
    public void modifyEmployee(ModifyEmployeeCommand command) {
        Employee employee = employeeDaoBean.findEmployeeById(command.getId());
        employee.setName(command.getName());
        employee.setSalary(command.getSalary());
    }

    public Employee findEmployeeById(long id) {
        return employeeDaoBean.findEmployeeById(id);
    }

    public List<Integer> listSalaryOptions() {
        return List.of(100_000, 200_000, 500_000);
    }

    public int countEmployeesWithName(String name) {
        return
                employeeDaoBean.countEmployeesWithName(name);
    }

    public void deleteEmployee(DeleteEmployeeCommand command) {
        employeeDaoBean.deleteById(command.getId());
    }
}
