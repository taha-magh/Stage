package ma.gov.marrakech.repository;

import ma.gov.marrakech.domain.SuiviSdl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SuiviSdl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SuiviSdlRepository extends JpaRepository<SuiviSdl, Long>,JpaSpecificationExecutor<SuiviSdl>  {
}
