package ma.gov.marrakech.service;

import ma.gov.marrakech.service.dto.StructureSdlDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StructureSdlService {
    /**
     * Save a structureSdl.
     *
     * @param structureSdlDTO the entity to save.
     * @return the persisted entity.
     */
    StructureSdlDTO save(StructureSdlDTO structureSdlDTO);

    /**
     * Updates a structureSdl.
     *
     * @param structureSdlDTO the entity to update.
     * @return the persisted entity.
     */
    StructureSdlDTO update(StructureSdlDTO structureSdlDTO);

    /**
     * Partially updates a structureSdl.
     *
     * @param structureSdlDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<StructureSdlDTO> partialUpdate(StructureSdlDTO structureSdlDTO);

    /**
     * Get all the structureSdls.
     *
     * @return the list of entities.
     */
    List<StructureSdlDTO> findAll(Pageable pageable);

    /**
     * Get the "id" structureSdl.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StructureSdlDTO> findOne(Long id);

    /**
     * Delete the "id" structureSdl.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
