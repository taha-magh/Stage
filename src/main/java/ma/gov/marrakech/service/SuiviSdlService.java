package ma.gov.marrakech.service;

import ma.gov.marrakech.service.dto.SuiviSdlDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SuiviSdlService {
    SuiviSdlDTO save(SuiviSdlDTO suiviSdlDTO);

    SuiviSdlDTO update(SuiviSdlDTO suiviSdlDTO);

    Optional<SuiviSdlDTO> partialUpdate(SuiviSdlDTO suiviSdlDTO);

    Page<SuiviSdlDTO> findAll(Pageable pageable);

    Optional<SuiviSdlDTO> findOne(Long id);

    void delete(Long id);
}
