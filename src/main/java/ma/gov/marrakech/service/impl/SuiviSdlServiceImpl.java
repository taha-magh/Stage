package ma.gov.marrakech.service.impl;

import ma.gov.marrakech.domain.SuiviSdl;
import ma.gov.marrakech.domain.SuiviSdl;
import ma.gov.marrakech.repository.SuiviSdlRepository;
import ma.gov.marrakech.repository.SuiviSdlRepository;
import ma.gov.marrakech.service.SuiviSdlService;
import ma.gov.marrakech.service.SuiviSdlService;
import ma.gov.marrakech.service.dto.SuiviSdlDTO;
import ma.gov.marrakech.service.dto.SuiviSdlDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SuiviSdlServiceImpl implements SuiviSdlService {
    private final Logger log = LoggerFactory.getLogger(DelegataireServiceImpl.class);
    private final Mapper mapper;
    private final SuiviSdlRepository suiviSdlRepository;
    public SuiviSdlServiceImpl(Mapper mapper, SuiviSdlRepository suiviSdlRepository) {
        this.mapper = mapper;
        this.suiviSdlRepository = suiviSdlRepository;
    }
    @Override
    public SuiviSdlDTO save(SuiviSdlDTO suiviSdlDTO) {
        log.debug("Request to save suiviSdl : {}", suiviSdlDTO);
        return mapper.map(suiviSdlRepository.save(
                mapper.map(suiviSdlDTO, SuiviSdl.class))
            , SuiviSdlDTO.class);
    }
    @Override
    public SuiviSdlDTO update(SuiviSdlDTO suiviSdlDTO) {
        log.debug("Request to update SuiviSdl : {}", suiviSdlDTO);
        return mapper.map(suiviSdlRepository.save(
                mapper.map(suiviSdlDTO, SuiviSdl.class))
            , SuiviSdlDTO.class);
    }

    @Override
    public Optional<SuiviSdlDTO> partialUpdate(SuiviSdlDTO suiviSdlDTO) {
        log.debug("Request to partially update SuiviSdl : {}", suiviSdlDTO);

        return suiviSdlRepository
            .findById(suiviSdlDTO.getId())
            .map(existingSuiviSdl -> {
                mapper.partialUpdate(existingSuiviSdl, suiviSdlDTO);

                return existingSuiviSdl ;
            })
            .map(suiviSdlRepository::save)
            .map(mapper::toDto);
    }


    @Override
    public Page<SuiviSdlDTO> findAll(Pageable pageable) {
        log.debug("Request to get all suiviSdl");
        return mapper.mapPageToOther(suiviSdlRepository.findAll(pageable)
            ,SuiviSdlDTO.class);
    }

    @Override
    public Optional<SuiviSdlDTO> findOne(Long id) {
        log.debug("Request to get Delegataire : {}", id);
        return Optional.ofNullable(mapper.map(suiviSdlRepository.findById(id).get()
            , SuiviSdlDTO.class));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete suiviSdl : {}", id);
        suiviSdlRepository.deleteById(id);
    }
}

