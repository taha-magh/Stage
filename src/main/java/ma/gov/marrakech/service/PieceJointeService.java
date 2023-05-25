package ma.gov.marrakech.service;

import ma.gov.marrakech.domain.PieceJointe;
import ma.gov.marrakech.service.dto.PieceJointeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link PieceJointe}.
 **/
    public interface PieceJointeService {

        /**
         * Save a pieceJointes.
         *
         * @param pieceJointeDTO the entity to save.
         * @return the persisted entity.
         */
        PieceJointeDTO save(PieceJointeDTO pieceJointeDTO);

        /**
         * Get all the pieceJointes.
         *
         * @return the list of entities.
         */
        List<PieceJointeDTO> findAll();


        /**
         * Get the "id" pieceJointe.
         *
         * @param id the id of the entity.
         * @return the entity.
         */
        Optional<PieceJointeDTO> findOne(Long id);


        /**
         * Delete the "id" pieceJointe.
         *
         * @param id the id of the entity.
         */
        void delete(Long id);
    }

