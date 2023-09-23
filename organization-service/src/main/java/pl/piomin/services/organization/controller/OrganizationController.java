package pl.piomin.services.organization.controller;

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
import pl.piomin.services.organization.client.DepartmentClient;
import pl.piomin.services.organization.client.EmployeeClient;
import pl.piomin.model.dto.OrganizationDto;
import pl.piomin.services.organization.entity.Organization;
import pl.piomin.services.organization.repository.OrganizationRepository;

@RestController
public class OrganizationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);
	
	@Autowired
	OrganizationRepository repository;
	@Autowired
	DepartmentClient departmentClient;
	@Autowired
	EmployeeClient employeeClient;
	
	@PostMapping("/")
	public OrganizationDto add(@RequestBody Organization organization) {
		LOGGER.info("Organization add: {}", organization);
		return repository.save(organization).toDto();
	}
	
	@GetMapping("/")
	public List<OrganizationDto> findAll() {
		LOGGER.info("Organization find");
		return repository.findAll().stream().map(Organization::toDto).toList();
	}
	
	@GetMapping("/{id}")
	public OrganizationDto findById(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization o = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return o.toDto();
	}

	@GetMapping("/{id}/with-departments")
	public OrganizationDto findByIdWithDepartments(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization o = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		OrganizationDto dto = o.toDto();
		dto.setDepartments(departmentClient.findByOrganization(dto.getId()));
		return dto;
	}
	
	@GetMapping("/{id}/with-departments-and-employees")
	public OrganizationDto findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization o = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		OrganizationDto dto = o.toDto();
		dto.setDepartments(departmentClient.findByOrganizationWithEmployees(dto.getId()));
		return dto;
	}
	
	@GetMapping("/{id}/with-employees")
	public OrganizationDto findByIdWithEmployees(@PathVariable("id") Long id) {
		LOGGER.info("Organization find: id={}", id);
		Organization o = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		OrganizationDto dto = o.toDto();
		dto.setEmployees(employeeClient.findByOrganization(dto.getId()));
		return dto;
	}
}
