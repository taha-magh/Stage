package ma.gov.marrakech.service;

import ma.gov.marrakech.service.dto.SuiviDelegataireDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SuiviDelegataireService {
    SuiviDelegataireDTO save(SuiviDelegataireDTO suiviDelegataireDTO);

    SuiviDelegataireDTO update(SuiviDelegataireDTO suiviDelegataireDTO);

    Optional<SuiviDelegataireDTO> partialUpdate(SuiviDelegataireDTO suiviDelegataireDTO);

    Page<SuiviDelegataireDTO> findAll(Pageable pageable);

    Optional<SuiviDelegataireDTO> findOne(Long id);

    void delete(Long id);
}
