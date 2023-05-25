package ma.gov.marrakech.service;

import ma.gov.marrakech.service.dto.SdlDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SdlService {
    /**
     * Save a sdl.
     *
     * @param sdlDTO the entity to save.
     * @return the persisted entity.
     */
    SdlDTO save(SdlDTO sdlDTO);

    /**
     * Updates a sdl.
     *
     * @param sdlDTO the entity to update.
     * @return the persisted entity.
     */
    SdlDTO update(SdlDTO sdlDTO);

    /**
     * Partially updates a sdl.
     *
     * @param sdlDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SdlDTO> partialUpdate(SdlDTO sdlDTO);

    /**
     * Get all the sdls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    Page<SdlDTO> findAll(Pageable pageable);

    /**
     * Get the "id" sdl.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    Optional<SdlDTO> findOne(Long id);

    /**
     * Delete the "id" sdl.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
