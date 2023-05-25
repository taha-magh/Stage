package ma.gov.marrakech.repository;

import ma.gov.marrakech.domain.PieceJointe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PieceJointe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PieceJointeRepository extends JpaRepository<PieceJointe, Long>,JpaSpecificationExecutor<PieceJointe>  {}
