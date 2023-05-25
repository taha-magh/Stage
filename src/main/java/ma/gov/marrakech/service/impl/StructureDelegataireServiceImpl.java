package ma.gov.marrakech.service.impl;



import ma.gov.marrakech.domain.Delegataire;
import ma.gov.marrakech.domain.StructureDelegataire;
import ma.gov.marrakech.repository.StructureDelegataireRepository;
import ma.gov.marrakech.service.StructureDelegataireService;
import ma.gov.marrakech.service.dto.DelegataireDTO;
import ma.gov.marrakech.service.dto.StructureDelegataireDTO;
import ma.gov.marrakech.service.dto.SuiviDelegataireDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link StructureDelegataire}.
 */
@Service
@Transactional
public class StructureDelegataireServiceImpl implements StructureDelegataireService {

    private final Logger log = LoggerFactory.getLogger(StructureDelegataireServiceImpl.class);

    private final StructureDelegataireRepository structureDelegataireRepository;

    private final Mapper mapper;

    public StructureDelegataireServiceImpl(StructureDelegataireRepository structureDelegataireRepository, Mapper mapper) {
        this.structureDelegataireRepository = structureDelegataireRepository;
        this.mapper = mapper;
    }
    /**
     * Save a structureDelegataire.
     *
     * @param structureDelegataireDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StructureDelegataireDTO save(StructureDelegataireDTO structureDelegataireDTO) {
        log.debug("Request to save StructureDelegataire : {}", structureDelegataireDTO);
        return mapper.map(structureDelegataireRepository.save(
                mapper.map(structureDelegataireDTO, StructureDelegataire.class))
            , StructureDelegataireDTO.class);
    }


    @Override
    public StructureDelegataireDTO update(StructureDelegataireDTO structureDelegataireDTO) {
        log.debug("Request to update StructureDelegataire : {}", structureDelegataireDTO);
        return mapper.map(structureDelegataireRepository.save(
                mapper.map(structureDelegataireDTO, StructureDelegataire.class))
            , StructureDelegataireDTO.class);
    }
    @Override
    public Optional<StructureDelegataireDTO> partialUpdate (StructureDelegataireDTO structureDelegataireDTO) {
        log.debug("Request to partially update StructureDelegataire : {}", structureDelegataireDTO);
        return structureDelegataireRepository.findById(structureDelegataireDTO.getId())
            .map(existingStructureDelegataire -> {
                mapper.partialUpdate(existingStructureDelegataire, structureDelegataireDTO);
                return existingStructureDelegataire;
            })
            .map(structureDelegataireRepository::save)
            .map(mapper::toDto);
    }

    /**
     * Get all the structureDelegataires.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<StructureDelegataireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StructureDelegataires");
        return mapper.mapList(structureDelegataireRepository.findAll(),
            StructureDelegataireDTO.class);
    }

    /**
     * Get one structureDelegataire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StructureDelegataireDTO> findOne(Long id) {
        log.debug("Request to get StructureDelegataire : {}", id);
        return Optional.ofNullable(mapper.map(structureDelegataireRepository.findById(id).get()
           , StructureDelegataireDTO.class));
    }

    /**
     * Delete the structureDelegataire by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StructureDelegataire : {}", id);
        structureDelegataireRepository.deleteById(id);
    }
}
