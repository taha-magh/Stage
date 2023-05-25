package ma.gov.marrakech.service;


import ma.gov.marrakech.domain.StructureDelegataire;
import ma.gov.marrakech.service.dto.StructureDelegataireDTO;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;
/**
 * Service Interface for managing {@link StructureDelegataire}.
 */
public interface StructureDelegataireService {
    /**
     * Save a structureDelegataire.
     *
     * @param structureDelegataireDTO the entity to save.
     * @return the persisted entity.
     */
    StructureDelegataireDTO save(StructureDelegataireDTO structureDelegataireDTO);

    /**
     * Partially updates a structureDelegataire.
     *
     * @param structureDelegataireDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StructureDelegataireDTO> partialUpdate(StructureDelegataireDTO structureDelegataireDTO);

    /**
     * Updates a structureDelegataire.
     *
     * @param structureDelegataireDTO the entity to update.
     * @return the persisted entity.
     */
    StructureDelegataireDTO update(StructureDelegataireDTO structureDelegataireDTO);

    /**
     * Get all the structureDelegataires.
     *
     * @return the list of entities.
     */
    List<StructureDelegataireDTO> findAll(Pageable pageable);

    /**
     * Get the "id" structureDelegataire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StructureDelegataireDTO> findOne(Long id);

    /**
     * Delete the "id" structureDelegataire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
