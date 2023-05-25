package ma.gov.marrakech.repository;

import ma.gov.marrakech.domain.Authority;
import ma.gov.marrakech.domain.Delegataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String>, JpaSpecificationExecutor<Authority> {
}
