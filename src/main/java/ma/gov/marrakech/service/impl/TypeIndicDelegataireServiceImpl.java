package ma.gov.marrakech.service.impl;


import ma.gov.marrakech.domain.TypeIndicDelegataire;
import ma.gov.marrakech.repository.TypeIndicDelegataireRepository;
import ma.gov.marrakech.service.TypeIndicDelegataireService;
import ma.gov.marrakech.service.dto.TypeIndicDelegataireDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TypeIndicDelegataire}.
 */
@Service
@Transactional
public class TypeIndicDelegataireServiceImpl implements TypeIndicDelegataireService {

    private final Logger log = LoggerFactory.getLogger(TypeIndicDelegataireServiceImpl.class);

    private final TypeIndicDelegataireRepository typeIndicDelegataireRepository;

    private final Mapper mapper;

    public TypeIndicDelegataireServiceImpl(TypeIndicDelegataireRepository typeIndicDelegataireRepository, Mapper mapper) {
        this.typeIndicDelegataireRepository = typeIndicDelegataireRepository;
        this.mapper = mapper;
    }

    @Override
    public TypeIndicDelegataireDTO save(TypeIndicDelegataireDTO typeIndicDelegataireDTO) {
        log.debug("Request to save typeIndicDelegataire : {}", typeIndicDelegataireDTO);
        return mapper.map(typeIndicDelegataireRepository.save(
                mapper.map(typeIndicDelegataireDTO, TypeIndicDelegataire.class))
            , TypeIndicDelegataireDTO.class);
    }
    @Override
    public TypeIndicDelegataireDTO update(TypeIndicDelegataireDTO typeIndicDelegataireDTO) {
        log.debug("Request to update typeIndicDelegataire : {}", typeIndicDelegataireDTO);
        return mapper.map(typeIndicDelegataireRepository.save(
                mapper.map(typeIndicDelegataireDTO, TypeIndicDelegataire.class))
            , TypeIndicDelegataireDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeIndicDelegataireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StructureDelegataires");
        return mapper.mapList(typeIndicDelegataireRepository.findAll(),
            TypeIndicDelegataireDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeIndicDelegataireDTO> findOne(Long id) {
        log.debug("Request to get TypeIndicDelegataire : {}", id);
       return Optional.ofNullable(mapper.map(typeIndicDelegataireRepository.findById(id).get()
           , TypeIndicDelegataireDTO .class));
    }


    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeIndicDelegataire : {}", id);
        typeIndicDelegataireRepository.deleteById(id);
    }
}
