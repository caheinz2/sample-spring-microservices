package pl.piomin.services.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.server.ResponseStatusException;
import pl.piomin.model.dto.EmployeeDto;
import pl.piomin.services.employee.entity.Employee;
import pl.piomin.services.employee.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeRepository repository;
	
	@PostMapping("/")
	public EmployeeDto add(@RequestBody Employee employee) {
		LOGGER.info("Employee add: {}", employee);
		return repository.save(employee).toDto();
	}
	
	@GetMapping("/{id}")
	public EmployeeDto findById(@PathVariable("id") Long id) {
		LOGGER.info("Employee find: id={}", id);
		Employee e = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return e.toDto();
	}
	
	@GetMapping("/")
	public List<EmployeeDto> findAll() {
		LOGGER.info("Employee find");
		return repository.findAll().stream().map(Employee::toDto).toList();
	}
	
	@GetMapping("/department/{departmentId}")
	public List<EmployeeDto> findByDepartment(@PathVariable("departmentId") Long departmentId) {
		LOGGER.info("Employee find: departmentId={}", departmentId);
		return repository.findEmployeesByDepartmentId(departmentId).stream().map(Employee::toDto).toList();
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<EmployeeDto> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Employee find: organizationId={}", organizationId);
		return repository.findEmployeesByOrganizationId(organizationId).stream().map(Employee::toDto).toList();
	}
	
}
