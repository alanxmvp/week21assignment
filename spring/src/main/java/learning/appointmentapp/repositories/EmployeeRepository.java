package learning.appointmentapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.appointmentapp.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  Employee findByEmail(String email);

  Optional<Employee> findByName(String name);
}