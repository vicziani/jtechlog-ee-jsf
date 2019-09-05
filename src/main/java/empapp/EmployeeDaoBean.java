package empapp;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class EmployeeDaoBean {

    @PersistenceContext
    private EntityManager em;

    public List<Employee> findEmployees() {
        return em.createNamedQuery("Employee.findEmployees", Employee.class).getResultList();
    }

    public void saveEmployee(Employee employee) {
        em.persist(employee);
    }

    public Employee findEmployeeById(long id) {
        return em.find(Employee.class, id);
    }

    public int countEmployeesWithName(String name) {
            return em.createNamedQuery("Employee.countEmployeesWithName")
                .setParameter("name", name).getFirstResult();
    }

    public void deleteById(long id) {
        Employee employee = em.getReference(Employee.class, id);
        em.remove(employee);
    }
}
