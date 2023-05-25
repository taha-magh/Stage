package ma.gov.marrakech.service.impl;

import ma.gov.marrakech.domain.Delegataire;
import ma.gov.marrakech.repository.DelegataireRepository;
import ma.gov.marrakech.service.DelegataireService;
import ma.gov.marrakech.service.dto.DelegataireDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Delegataire}.
 */
@Service
@Transactional
public class DelegataireServiceImpl implements DelegataireService {

  private final Logger log = LoggerFactory.getLogger(DelegataireServiceImpl.class);

  private final DelegataireRepository delegataireRepository;

  private final Mapper mapper;

  public DelegataireServiceImpl(DelegataireRepository delegataireRepository, Mapper mapper) {
    this.delegataireRepository = delegataireRepository;
    this.mapper = mapper;
  }

    @Override
    public DelegataireDTO save(DelegataireDTO delegataireDTO) {
        log.debug("Request to save Delegataire : {}", delegataireDTO);
        return mapper.map(delegataireRepository.save(
                mapper.map(delegataireDTO, Delegataire.class))
            , DelegataireDTO.class);
    }

    @Override
    public DelegataireDTO update(DelegataireDTO delegataireDTO) {
        log.debug("Request to update Delegataire : {}", delegataireDTO);
        return mapper.map(delegataireRepository.save(
                mapper.map(delegataireDTO, Delegataire.class))
            , DelegataireDTO.class);
    }


    @Override
    public Optional<DelegataireDTO> partialUpdate(DelegataireDTO delegataireDTO) {
        log.debug("Request to partially update Delegataire : {}", delegataireDTO);

        return delegataireRepository
            .findById(delegataireDTO.getId())
            .map(existingDelegataire -> {
                mapper.partialUpdate(existingDelegataire, delegataireDTO);

                return existingDelegataire ;
            })
            .map(delegataireRepository::save)
            .map(mapper::toDto);
    }

    @Override
    public Page<DelegataireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Delegataire");
        return mapper.mapPageToOther(delegataireRepository.findAll(pageable)
            , DelegataireDTO.class);
    }

    @Override
    public Optional<DelegataireDTO> findOne(Long id) {
        log.debug("Request to get Delegataire : {}", id);
        return Optional.ofNullable(mapper.map(delegataireRepository.findById(id).get()
            , DelegataireDTO.class));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete delegataire : {}", id);
        delegataireRepository.deleteById(id);
    }
}
