package ma.gov.marrakech.service.impl;


import ma.gov.marrakech.domain.Sdl;
import ma.gov.marrakech.domain.Sdl;
import ma.gov.marrakech.repository.SdlRepository;
import ma.gov.marrakech.repository.SdlRepository;
import ma.gov.marrakech.service.SdlService;
import ma.gov.marrakech.service.dto.SdlDTO;
import ma.gov.marrakech.service.dto.SdlDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Sdl}.
 */

@Service
@Transactional
public class SdlServiceImpl implements SdlService {

    private final Logger log = LoggerFactory.getLogger(SdlServiceImpl.class);

    private final SdlRepository sdlRepository;

    private final Mapper mapper;

    public SdlServiceImpl(SdlRepository sdlRepository, Mapper mapper) {
        this.sdlRepository = sdlRepository;
        this.mapper = mapper;
    }

    @Override
    public SdlDTO save(SdlDTO sdlDTO) {
        log.debug("Request to save Sdl : {}", sdlDTO);
        return mapper.map(sdlRepository.save(
                mapper.map(sdlDTO, Sdl.class))
            , SdlDTO.class);
    }

    @Override
    public SdlDTO update(SdlDTO sdlDTO) {
        log.debug("Request to update Sdl : {}", sdlDTO);
        return mapper.map(sdlRepository.save(
                mapper.map(sdlDTO, Sdl.class))
            , SdlDTO.class);
    }


    @Override
    public Optional<SdlDTO> partialUpdate(SdlDTO sdlDTO) {
        log.debug("Request to partially update Sdl : {}", sdlDTO);
        return sdlRepository
            .findById(sdlDTO.getId())
            .map(existingSdl -> {
                mapper.partialUpdate(existingSdl, sdlDTO);
                return existingSdl;
            })
            .map(sdlRepository::save)
            .map(mapper::toDto);
    }

    @Override
    public Page<SdlDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sdl");
        return mapper.mapPageToOther(sdlRepository.findAll(pageable)
            , SdlDTO.class);
    }

    @Override
    public Optional<SdlDTO> findOne(Long id) {
        log.debug("Request to get Sdl : {}", id);
        return Optional.ofNullable(mapper.map(sdlRepository.findById(id).get()
            , SdlDTO.class));

    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete sdl : {}", id);
        sdlRepository.deleteById(id);
    }
}

