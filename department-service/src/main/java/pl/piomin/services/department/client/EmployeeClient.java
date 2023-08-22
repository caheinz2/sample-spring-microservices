package pl.piomin.services.department.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.piomin.model.dto.EmployeeDto;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

	@GetMapping("/department/{departmentId}")
	List<EmployeeDto> findByDepartment(@PathVariable("departmentId") Long departmentId);
	
}
