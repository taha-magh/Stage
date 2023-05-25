package ma.gov.marrakech.service;


import ma.gov.marrakech.service.dto.DelegataireDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DelegataireService {

    /**
     * Save a delegataire.
     *
     * @param delegataireDTO the entity to save.
     * @return the persisted entity.
     */
    DelegataireDTO save(DelegataireDTO delegataireDTO);

    /**
     * Updates a delegataire.
     *
     * @param delegataireDTO the entity to update.
     * @return the persisted entity.
     */
    DelegataireDTO update(DelegataireDTO delegataireDTO);

    /**
     * Partially updates a delegataire.
     *
     * @param delegataireDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DelegataireDTO> partialUpdate(DelegataireDTO delegataireDTO);

    /**
     * Get all the delegataires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DelegataireDTO> findAll(Pageable pageable);

    /**
     * Get the "id" delegataire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DelegataireDTO> findOne(Long id);

    /**
     * Delete the "id" delegataire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
