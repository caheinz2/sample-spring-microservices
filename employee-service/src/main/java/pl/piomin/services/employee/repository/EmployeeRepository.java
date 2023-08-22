package pl.piomin.services.employee.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import pl.piomin.services.employee.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findAll();

	List<Employee> findEmployeesByDepartmentId(Long departmentId);

	List<Employee> findEmployeesByOrganizationId(Long organizationId);
}
