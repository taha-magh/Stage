package ma.gov.marrakech.repository;

import ma.gov.marrakech.domain.SuiviDelegataire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SuiviDelegataire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuiviDelegataireRepository extends JpaRepository<SuiviDelegataire, Long>,JpaSpecificationExecutor<SuiviDelegataire>  {
}
