package ma.gov.marrakech.service.impl;

import ma.gov.marrakech.domain.TypeIndicSdl;
import ma.gov.marrakech.repository.TypeIndicSdlRepository;
import ma.gov.marrakech.service.TypeIndicSdlService;
import ma.gov.marrakech.service.dto.TypeIndicSdlDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeIndicSdl}.
 */
@Service
@Transactional
public class TypeIndicSdlServiceImpl implements TypeIndicSdlService {

    private final Logger log = LoggerFactory.getLogger(TypeIndicSdlServiceImpl.class);

    private final TypeIndicSdlRepository typeIndicSdlRepository;

    private final Mapper mapper;

    public TypeIndicSdlServiceImpl(TypeIndicSdlRepository typeIndicSdlRepository, Mapper mapper) {
        this.typeIndicSdlRepository = typeIndicSdlRepository;
        this.mapper = mapper;
    }

    @Override
    public TypeIndicSdlDTO save(TypeIndicSdlDTO typeIndicSdlDTO) {
        log.debug("Request to save typeIndicSdl : {}", typeIndicSdlDTO);
        return mapper.map(typeIndicSdlRepository.save(
                mapper.map(typeIndicSdlDTO, TypeIndicSdl.class))
            , TypeIndicSdlDTO.class);
    }

    @Override
    public TypeIndicSdlDTO update(TypeIndicSdlDTO typeIndicSdlDTO) {
        log.debug("Request to update typeIndicSdl : {}", typeIndicSdlDTO);
        return mapper.map(typeIndicSdlRepository.save(
                mapper.map(typeIndicSdlDTO, TypeIndicSdl.class))
            , TypeIndicSdlDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeIndicSdlDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StructureSdls");
        return mapper.mapList(typeIndicSdlRepository.findAll(),
            TypeIndicSdlDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeIndicSdlDTO> findOne(Long id) {
        log.debug("Request to get TypeIndicSdl : {}", id);
        return Optional.ofNullable(mapper.map(typeIndicSdlRepository.findById(id).get()
            , TypeIndicSdlDTO .class));
    }


    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeIndicSdl : {}", id);
        typeIndicSdlRepository.deleteById(id);
    }
}
