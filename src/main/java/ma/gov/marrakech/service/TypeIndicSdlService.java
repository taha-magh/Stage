package ma.gov.marrakech.service;

import ma.gov.marrakech.service.dto.TypeIndicSdlDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ma.gov.marrakech.domain.TypeIndicSdl}.
 */
public interface TypeIndicSdlService {

    /**
     * Save a typeIndicSdlDTO.
     *
     * @param typeIndicSdlDTO the entity to save.
     * @return the persisted entity.
     */
    TypeIndicSdlDTO save(TypeIndicSdlDTO typeIndicSdlDTO);

    /**
     * Update a typeIndicSdlDTO.
     *
     * @param typeIndicSdlDTO the entity to update.
     * @return the persisted entity.
     */
    TypeIndicSdlDTO update(TypeIndicSdlDTO typeIndicSdlDTO);

    /**
     * Get all the typeIndicSdlDTOS.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    List<TypeIndicSdlDTO> findAll(Pageable pageable);

    /**
     * Get the "id" typeIndicSdlDTO.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeIndicSdlDTO> findOne(Long id);

    /**
     * Delete the "id" typeIndicSdlDTO.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
