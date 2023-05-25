package ma.gov.marrakech.service;

import ma.gov.marrakech.service.dto.TypeIndicDelegataireDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ma.gov.marrakech.domain.TypeIndicDelegataire}.
 */
public interface TypeIndicDelegataireService {
        /**
         * Save a typeIndicDelegataireDTO.
         *
         * @param typeIndicDelegataireDTO the entity to save.
         * @return the persisted entity.
         */
        TypeIndicDelegataireDTO save(TypeIndicDelegataireDTO typeIndicDelegataireDTO);

        /**
           * Update a typeIndicDelegataireDTO.
           *
           * @param typeIndicDelegataireDTO the entity to update.
           * @return the persisted entity.
        */
        TypeIndicDelegataireDTO update(TypeIndicDelegataireDTO typeIndicDelegataireDTO);

    /**
         * Get all the typeIndicDelegataireDTOS.
         *
         * @return the list of entities.
         */
        List<TypeIndicDelegataireDTO> findAll(Pageable pageable);

    /**
         * Get the "id" typeIndicDelegataireDTO.
         *
         * @param id the id of the entity.
         * @return the entity.
         */
        Optional<TypeIndicDelegataireDTO> findOne(Long id);

        /**
         * Delete the "id" typeIndicDelegataireDTO.
         *
         * @param id the id of the entity.
         */
        void delete(Long id);
    }

