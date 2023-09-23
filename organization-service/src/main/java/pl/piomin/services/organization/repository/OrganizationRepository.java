package pl.piomin.services.organization.repository;

import org.springframework.data.repository.CrudRepository;
import pl.piomin.services.organization.entity.Organization;

import java.util.List;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
    List<Organization> findAll();
}
