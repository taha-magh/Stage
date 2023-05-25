package ma.gov.marrakech.service.impl;

import ma.gov.marrakech.domain.StructureSdl;
import ma.gov.marrakech.domain.StructureSdl;
import ma.gov.marrakech.domain.StructureSdl;
import ma.gov.marrakech.repository.StructureSdlRepository;
import ma.gov.marrakech.repository.StructureSdlRepository;
import ma.gov.marrakech.service.StructureSdlService;
import ma.gov.marrakech.service.StructureSdlService;
import ma.gov.marrakech.service.dto.StructureSdlDTO;
import ma.gov.marrakech.service.dto.StructureSdlDTO;
import ma.gov.marrakech.service.dto.StructureSdlDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StructureSdlServiceImpl implements StructureSdlService {
    private final Logger log = LoggerFactory.getLogger(StructureSdlServiceImpl.class);

    private final StructureSdlRepository structureSdlRepository;

    private final Mapper mapper;

    public StructureSdlServiceImpl(StructureSdlRepository structureSdlRepository, Mapper mapper) {
        this.structureSdlRepository = structureSdlRepository;
        this.mapper = mapper;
    }
    /**
     * Save a structureSdl.
     *
     * @param structureSdlDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StructureSdlDTO save(StructureSdlDTO structureSdlDTO) {
        log.debug("Request to save StructureSdl : {}", structureSdlDTO);
        return mapper.map(structureSdlRepository.save(
                mapper.map(structureSdlDTO, StructureSdl.class))
            , StructureSdlDTO.class);
    }


    @Override
    public StructureSdlDTO update(StructureSdlDTO structureSdlDTO) {
        log.debug("Request to update StructureSdl : {}", structureSdlDTO);
        return mapper.map(structureSdlRepository.save(
                mapper.map(structureSdlDTO, StructureSdl.class))
            , StructureSdlDTO.class);
    }
    @Override
    public Optional<StructureSdlDTO> partialUpdate (StructureSdlDTO structureSdlDTO) {
        log.debug("Request to partially update StructureSdl : {}", structureSdlDTO);
        return structureSdlRepository.findById(structureSdlDTO.getId())
            .map(existingStructureSdl -> {
                mapper.partialUpdate(existingStructureSdl, structureSdlDTO);
                return existingStructureSdl;
            })
            .map(structureSdlRepository::save)
            .map(mapper::toDto);
    }

    /**
     * Get all the structureSdls.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<StructureSdlDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StructureSdls");
        return mapper.mapList(structureSdlRepository.findAll(),
            StructureSdlDTO.class);
    }

    /**
     * Get one structureSdl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StructureSdlDTO> findOne(Long id) {
        log.debug("Request to get StructureSdl : {}", id);
        return Optional.ofNullable(mapper.map(structureSdlRepository.findById(id).get()
            , StructureSdlDTO.class));
    }

    /**
     * Delete the structureSdl by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StructureSdl : {}", id);
        structureSdlRepository.deleteById(id);
    }
}
