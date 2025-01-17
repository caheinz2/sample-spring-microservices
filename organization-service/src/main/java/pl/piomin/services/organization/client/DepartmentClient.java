package pl.piomin.services.organization.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.piomin.model.dto.DepartmentDto;

@FeignClient(name = "department-service")
public interface DepartmentClient {

	@GetMapping("/organization/{organizationId}")
	public List<DepartmentDto> findByOrganization(@PathVariable("organizationId") Long organizationId);
	
	@GetMapping("/organization/{organizationId}/with-employees")
	public List<DepartmentDto> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId);
	
}
