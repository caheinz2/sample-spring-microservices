package pl.piomin.services.department.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.piomin.services.department.entity.Department;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
	List<Department> findAll();
	
	List<Department> findDepartmentsByOrganizationId(Long organizationId);
}
