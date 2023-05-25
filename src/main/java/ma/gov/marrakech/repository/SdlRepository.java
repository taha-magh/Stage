package ma.gov.marrakech.repository;

import ma.gov.marrakech.domain.Sdl;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Sdl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SdlRepository extends JpaRepository<Sdl, Long>,JpaSpecificationExecutor<Sdl> {
}
