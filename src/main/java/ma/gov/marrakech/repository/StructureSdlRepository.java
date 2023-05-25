package ma.gov.marrakech.repository;

import ma.gov.marrakech.domain.StructureSdl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StructureSdl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StructureSdlRepository extends JpaRepository<StructureSdl, Long>,JpaSpecificationExecutor<StructureSdl>  {
}
