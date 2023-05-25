package ma.gov.marrakech.repository;

import ma.gov.marrakech.domain.TypeIndicSdl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeIndicSdl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeIndicSdlRepository extends JpaRepository<TypeIndicSdl, Long>, JpaSpecificationExecutor<TypeIndicSdl> {
}
