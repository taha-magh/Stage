package ma.gov.marrakech.repository;

import ma.gov.marrakech.domain.StructureDelegataire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StructureDelegataire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StructureDelegataireRepository extends JpaRepository<StructureDelegataire, Long>,JpaSpecificationExecutor<StructureDelegataire> {
}
