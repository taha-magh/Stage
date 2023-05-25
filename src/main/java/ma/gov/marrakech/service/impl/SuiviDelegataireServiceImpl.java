package ma.gov.marrakech.service.impl;

import ma.gov.marrakech.domain.SuiviDelegataire;

import ma.gov.marrakech.repository.SuiviDelegataireRepository;
import ma.gov.marrakech.service.SuiviDelegataireService;
import ma.gov.marrakech.service.dto.DelegataireDTO;
import ma.gov.marrakech.service.dto.SuiviDelegataireDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SuiviDelegataire}.
 */
@Service
@Transactional
public class SuiviDelegataireServiceImpl implements SuiviDelegataireService {
    private final Logger log = LoggerFactory.getLogger(DelegataireServiceImpl.class);
    private final Mapper mapper;
    private final SuiviDelegataireRepository suiviDelegataireRepository;
    public SuiviDelegataireServiceImpl(Mapper mapper, SuiviDelegataireRepository suiviDelegataireRepository) {
        this.mapper = mapper;
        this.suiviDelegataireRepository = suiviDelegataireRepository;
    }
    @Override
    public SuiviDelegataireDTO save(SuiviDelegataireDTO suiviDelegataireDTO) {
        log.debug("Request to save suiviDelegataire : {}", suiviDelegataireDTO);
        return mapper.map(suiviDelegataireRepository.save(
                mapper.map(suiviDelegataireDTO, SuiviDelegataire.class))
            , SuiviDelegataireDTO.class);
    }
    @Override
    public SuiviDelegataireDTO update(SuiviDelegataireDTO suiviDelegataireDTO) {
        log.debug("Request to update SuiviDelegataire : {}", suiviDelegataireDTO);
        return mapper.map(suiviDelegataireRepository.save(
                mapper.map(suiviDelegataireDTO, SuiviDelegataire.class))
            , SuiviDelegataireDTO.class);
    }

    @Override
    public Optional<SuiviDelegataireDTO> partialUpdate(SuiviDelegataireDTO suiviDelegataireDTO) {
        log.debug("Request to partially update SuiviDelegataire : {}", suiviDelegataireDTO);

        return suiviDelegataireRepository
            .findById(suiviDelegataireDTO.getId())
            .map(existingSuiviDelegataire -> {
                mapper.partialUpdate(existingSuiviDelegataire, suiviDelegataireDTO);

                return existingSuiviDelegataire ;
            })
            .map(suiviDelegataireRepository::save)
            .map(mapper::toDto);
    }


    @Override
    public Page<SuiviDelegataireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all suiviDelegataire");
        return mapper.mapPageToOther(suiviDelegataireRepository.findAll(pageable)
            ,SuiviDelegataireDTO.class);
    }

    @Override
    public Optional<SuiviDelegataireDTO> findOne(Long id) {
        log.debug("Request to get Delegataire : {}", id);
        return Optional.ofNullable(mapper.map(suiviDelegataireRepository.findById(id).get()
            , SuiviDelegataireDTO.class));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete suiviDelegataire : {}", id);
        suiviDelegataireRepository.deleteById(id);
    }
}
