package ma.gov.marrakech.service.impl;


import ma.gov.marrakech.domain.PieceJointe;
import ma.gov.marrakech.repository.PieceJointeRepository;
import ma.gov.marrakech.service.PieceJointeService;
import ma.gov.marrakech.service.dto.PieceJointeDTO;

import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link PieceJointe}.
 */
@Service
@Transactional
public class PieceJointeServiceImpl implements PieceJointeService {

    private final Logger log = LoggerFactory.getLogger(PieceJointeServiceImpl.class);

    private final PieceJointeRepository pieceJointeRepository;
    private final Mapper mapper;

    public PieceJointeServiceImpl(PieceJointeRepository pieceJointeRepository, Mapper mapper) {
        this.pieceJointeRepository = pieceJointeRepository;
        this.mapper = mapper;
    }

    @Override
    public PieceJointeDTO save(PieceJointeDTO pieceJointeDTO) {
        log.debug("Request to save PieceJointe : {}", pieceJointeDTO);
        return mapper.map(pieceJointeRepository.save(
            mapper.map(pieceJointeDTO,PieceJointe.class))
            ,PieceJointeDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PieceJointeDTO> findAll() {
        log.debug("Request to get all PieceJointeS");
        return mapper.mapList(pieceJointeRepository.findAll()
            , PieceJointeDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PieceJointeDTO> findOne(Long id) {
        log.debug("Request to get PieceJointe : {}", id);
        return Optional.ofNullable(mapper.map(pieceJointeRepository.findById(id).get()
            , PieceJointeDTO.class));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PieceJointe : {}", id);
        pieceJointeRepository.deleteById(id);
    }
}
