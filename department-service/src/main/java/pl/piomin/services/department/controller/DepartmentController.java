package pl.piomin.services.department.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.piomin.model.dto.DepartmentDto;
import pl.piomin.services.department.client.EmployeeClient;
import pl.piomin.services.department.entity.Department;
import pl.piomin.services.department.repository.DepartmentRepository;

import java.util.List;

@RestController
public class DepartmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	DepartmentRepository repository;
	EmployeeClient employeeClient;

	public DepartmentController(DepartmentRepository repository, EmployeeClient employeeClient) {
		this.repository = repository;
		this.employeeClient = employeeClient;
	}

	@PostMapping("/")
	public DepartmentDto add(@RequestBody Department department) {
		LOGGER.info("Department add: {}", department);
		return repository.save(department).toDto();
	}
	
	@GetMapping("/{id}")
	public DepartmentDto findById(@PathVariable("id") Long id) {
		LOGGER.info("Department find: id={}", id);
		Department d = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return d.toDto();
	}
	
	@GetMapping("/")
	public List<DepartmentDto> findAll() {
		LOGGER.info("Department find");
		return repository.findAll().stream().map(Department::toDto).toList();
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<DepartmentDto> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		return repository.findDepartmentsByOrganizationId(organizationId).stream().map(Department::toDto).toList();
	}
	
	@GetMapping("/organization/{organizationId}/with-employees")
	public List<DepartmentDto> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		List<DepartmentDto> dtos = repository.findDepartmentsByOrganizationId(organizationId).stream().map(Department::toDto).toList();
		dtos.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
		return dtos;
	}
}
