package it.formarete.test;

import java.util.List;

import it.formarete.model.Employee;
import it.formarete.service.HibernateUtils;
import it.formarete.service.EmployeeDAO;
import junit.framework.TestCase;

public class EmployeeDAOTestCase extends TestCase {
	public void setUp() {
		HibernateUtils.getSessionFactory();
		
		EmployeeDAO dao = new EmployeeDAO();
		List<Employee> clearEmployees = dao.getAll();
		for(Employee clearEmployee: clearEmployees) {
			dao.delete(clearEmployee);
		}
	}
	
	public void testEmployeeCrud() {
		Employee employee = new Employee();
		employee.setName("Antony");
		employee.setSalary(1000);
		
		// retrieve
		EmployeeDAO dao = new EmployeeDAO();
		List<Employee> employees = dao.getAll();
		assertNotNull(employees);
		assertTrue(employees.isEmpty());
		
		//create
		int id = dao.save(employee);
		assertTrue(id > 0);
		employee = dao.get(id);
		assertEquals(id, employee.getId());
		employees = dao.getAll();
		assertEquals(employees.size(), 1);
		
		//update
		employee.setSalary(employee.getSalary()*2);
		dao.update(employee);
		employee = dao.get(employee.getId());
		assertEquals(2000f, employee.getSalary());
		employees = dao.getAll();
		assertEquals(employees.size(), 1);

		//delete
		dao.delete(employee);
		employee = dao.get(employee.getId());
		assertNull(employee);
		employees = dao.getAll();
		assertNotNull(employees);
		assertTrue(employees.isEmpty());
	
	}
	
	public void tearDown() {
		HibernateUtils.closeSessionFactory();
	}
}
