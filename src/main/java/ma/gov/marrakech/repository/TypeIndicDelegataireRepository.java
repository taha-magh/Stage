package ma.gov.marrakech.repository;

import ma.gov.marrakech.domain.TypeIndicDelegataire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeIndicDelegataire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeIndicDelegataireRepository extends JpaRepository<TypeIndicDelegataire, Long>, JpaSpecificationExecutor<TypeIndicDelegataire>  {
}
