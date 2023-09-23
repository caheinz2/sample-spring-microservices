package pl.piomin.services.department.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import pl.piomin.model.dto.DepartmentDto;

@Entity
public class Department {

	@Id
	@GeneratedValue
	private Long id;
	private Long organizationId;
	private String name;

	public Department() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", organizationId=" + organizationId + ", name=" + name + "]";
	}

	public DepartmentDto toDto() {
		DepartmentDto dto = new DepartmentDto();
		dto.setId(id);
		dto.setName(name);
		dto.setOrganizationId(organizationId);
		return dto;
	}

}
