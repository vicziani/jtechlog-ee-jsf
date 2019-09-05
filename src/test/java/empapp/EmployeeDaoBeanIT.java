package empapp;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class EmployeeDaoBeanIT {

    @Inject
    private EmployeeDaoBean employeeDaoBean;

    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(Employee.class, EmployeeDaoBean.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

//    @Before
//    @Transactional
//    public void deleteEmployees() {
//        em.createQuery("delete from Employee").executeUpdate();
//    }

    @Test
    public void testSaveThenList() {
        employeeDaoBean.saveEmployee(new Employee("John Doe", 100_000));
        List<Employee> employees = employeeDaoBean.findEmployees();
        Employee employee = employees.stream()
                .filter(e -> e.getName().equals("John Doe"))
                .findFirst().get();
        assertEquals(100_000, employee.getSalary());
    }

}
