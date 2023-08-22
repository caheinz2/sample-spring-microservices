package pl.piomin.services.organization.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import pl.piomin.model.dto.OrganizationDto;

@Entity
public class Organization {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

	public OrganizationDto toDto() {
		OrganizationDto dto = new OrganizationDto();
		dto.setId(id);
		dto.setName(name);
		dto.setAddress(address);
		return dto;
	}

}
