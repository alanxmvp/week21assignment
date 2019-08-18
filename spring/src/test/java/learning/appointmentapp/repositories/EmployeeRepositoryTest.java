package learning.appointmentapp.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.data.domain.Sort;

import learning.appointmentapp.entities.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EmployeeRepositoryTest {

  @Autowired
  EmployeeRepository repo;

  @Test
  public void testFindAllEmpty() {
    // Given: there are no employees in the DB
    repo.deleteAll();

    // When: employeeRepository.findAll()
    List<Employee> result = repo.findAll();

    // Then: get an empty array
    assertEquals(0, result.size());
  }

  // Given: there are employees in the DB
  // When: employeeRepository.findAll()
  // Then: get an array of the employees

  @Test
  public void testFindAllNotEmpty() {
    // Given: there are employees in the DB
    seedEmployee();

    // When: employeeRepository.findAll()
    List<Employee> result = repo.findAll();

    // Then: get an array of the employees
    assertEquals(1, result.size());

    Employee retrievedEmployee = result.get(0);
    assertEquals("John", retrievedEmployee.getName());
    assertEquals("john@gmail.com", retrievedEmployee.getEmail());
  }

  public void testFindAll() {
    // Given: there are employees in the DB
    seedEmployee();

    // When: employeeRepository.findAll()
    List<Employee> result = repo.findAll();

    // Then: get an array of the employees
    assertEquals(1, result.size());

    Employee retrievedEmployee = result.get(0);
    assertEquals("John", retrievedEmployee.getName());
    assertEquals("john@gmail.com", retrievedEmployee.getEmail());
  }

  @Test
  public void testFindByIdWrongId() {
    // Given: wrong id
    repo.deleteAll();
    Long wrongId = 5L;

    Employee result = repo.findById(wrongId).orElse(null);

    assertEquals(null, result);
  }

  @Test
  public void testFindByIdCorrectId() {
    // Given: create employee and get its ID
    Employee seeded = seedEmployee();

    Employee result = repo.findById(seeded.getId()).orElse(null);

    assertEquals(seeded.getId(), result.getId());
  }

  @Test
  public void testFindByEmailCorrectEmail() {
    Employee seeded = seedEmployee();

    Employee result = repo.findByEmail(seeded.getEmail());

    assertEquals(seeded.getId(), result.getId());
  }

  @Test
  public void testFindByEmailInCorrectEmail() {
    repo.deleteAll();
    String wrongEmail = "john@gmail.com";

    Employee result = repo.findByEmail(wrongEmail);

    assertEquals(null, result);
  }

  @Test
  public void testFindAllSorted() {
    seedEmployeeWithName("John");
    seedEmployeeWithName("Jane");

    Sort sorting = new Sort(Sort.Direction.DESC, "name");
    List<Employee> result = repo.findAll(sorting);

    // Then?
    assertEquals("John", result.get(0).getName());
    assertEquals("Jane", result.get(1).getName());
  }

  public Employee seedEmployee() {
    Employee seeded = new Employee();
    seeded.setName("John");
    seeded.setEmail("john@gmail.com");
    repo.save(seeded);
    return seeded;
  }

  public Employee seedEmployeeWithName(String name) {
    Employee seeded = new Employee();
    seeded.setName(name);
    seeded.setEmail("john@gmail.com");
    repo.save(seeded);
    return seeded;
  }
}