package ma.gov.marrakech.repository;

import ma.gov.marrakech.domain.Delegataire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Delegataire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DelegataireRepository extends JpaRepository<Delegataire, Long>,JpaSpecificationExecutor<Delegataire>{
}
