package pl.piomin.services.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import pl.piomin.model.dto.EmployeeDto;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private Long id;
	private Long organizationId;
	private Long departmentId;
	private String name;
	private int age;
	private String position;

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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", organizationId=" + organizationId + ", departmentId=" + departmentId
				+ ", name=" + name + ", position=" + position + "]";
	}

	public EmployeeDto toDto() {
		EmployeeDto dto = new EmployeeDto();
		dto.setId(id);
		dto.setOrganizationId(organizationId);
		dto.setDepartmentId(departmentId);
		dto.setName(name);
		dto.setAge(age);
		dto.setPosition(position);
		return dto;
	}

}
