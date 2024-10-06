package cercli.repository;

import cercli.models.Employee;
import cercli.models.EmployeeUpdate;
import cercli.repository.exceptions.EmployeeNotFoundException;
import cercli.repository.exceptions.EmployeeUpdateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PostgresEmployeeDatabase implements EmployeeDatabase {

    private EntityManagerFactory emf;

    public PostgresEmployeeDatabase() {
        this.emf = Persistence.createEntityManagerFactory("employee-persistence-unit");
    }

    @Override
    public Employee addEmployee(Employee employee) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        } finally {
            em.close();
        }
    }

    @Override
    public Employee updateEmployee(EmployeeUpdate employeeUpdate) throws EmployeeNotFoundException, EmployeeUpdateException {
        EntityManager em = emf.createEntityManager();
        Employee employee = em.find(Employee.class, employeeUpdate.getEmployeeId());

        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found");
        }

        em.getTransaction().begin();

        // Reflection to update fields
        Field[] fields = EmployeeUpdate.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);

                // Proceed for Optional fields
                if (field.getType().equals(Optional.class)) {
                    Optional<?> value = (Optional<?>) field.get(employeeUpdate);

                    if (value.isPresent()) {
                        String fieldName = field.getName();
                        Field employeeField = Employee.class.getDeclaredField(fieldName);
                        employeeField.setAccessible(true);
                        employeeField.set(employee, value.get());
                    }
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                throw new EmployeeUpdateException(e.getMessage());
            }
        }

        em.merge(employee);
        em.getTransaction().commit();
        em.close();
        return employee;
    }

    @Override
    public Employee getEmployee(String employeeId) throws EmployeeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Employee e = em.find(Employee.class, UUID.fromString(employeeId));
        if (e == null) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        em.close();
        return e;
    }

    @Override
    public List<Employee> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}

