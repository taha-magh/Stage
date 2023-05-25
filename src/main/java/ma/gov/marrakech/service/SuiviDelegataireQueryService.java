package ma.gov.marrakech.service;

import io.github.jhipster.service.QueryService;
import ma.gov.marrakech.domain.SuiviDelegataire;
import ma.gov.marrakech.domain.SuiviDelegataire;
import ma.gov.marrakech.domain.SuiviDelegataire;
import ma.gov.marrakech.domain.SuiviDelegataire_;
import ma.gov.marrakech.repository.SuiviDelegataireRepository;
import ma.gov.marrakech.service.criteria.SuiviDelegataireCriteria;
import ma.gov.marrakech.service.criteria.SuiviDelegataireCriteria;
import ma.gov.marrakech.service.criteria.SuiviDelegataireCriteria;
import ma.gov.marrakech.service.dto.SuiviDelegataireDTO;
import ma.gov.marrakech.service.dto.SuiviDelegataireDTO;
import ma.gov.marrakech.service.dto.SuiviDelegataireDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SuiviDelegataireQueryService extends QueryService<SuiviDelegataire> {
    private final Logger log = LoggerFactory.getLogger(SuiviDelegataireQueryService.class);

    private final SuiviDelegataireRepository suiviDelegataireRepository;

    private final Mapper mapper;

    public SuiviDelegataireQueryService(SuiviDelegataireRepository suiviDelegataireRepository, Mapper mapper) {
        this.suiviDelegataireRepository = suiviDelegataireRepository;
        this.mapper = mapper;
    }

    /**
    *Return a {@link List} of {@link SuiviDelegataireDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SuiviDelegataireDTO> findByCriteria(SuiviDelegataireCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SuiviDelegataire> specification = createSpecification(criteria);
        return this.mapper.mapList(suiviDelegataireRepository.findAll(specification), SuiviDelegataireDTO.class);
    }


    /**
     * Return a {@link Page} of {@link SuiviDelegataireDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SuiviDelegataireDTO> findByCriteria(SuiviDelegataireCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SuiviDelegataire> specification = createSpecification(criteria);
        return mapper.mapPageToOther(suiviDelegataireRepository.findAll(specification, page),SuiviDelegataireDTO.class);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SuiviDelegataireCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SuiviDelegataire> specification = createSpecification(criteria);
        return suiviDelegataireRepository.count(specification);
    }

    /**
     * Function to convert {@link SuiviDelegataireCriteria} to a {@link Specification}.
     */
    private Specification<SuiviDelegataire> createSpecification(SuiviDelegataireCriteria criteria) {
        Specification<SuiviDelegataire> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SuiviDelegataire_.id));
            }
            if (criteria.getTypeIndicateur() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeIndicateur(), SuiviDelegataire_.typeIndicateur));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), SuiviDelegataire_.nom));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), SuiviDelegataire_.description));
            }
            if (criteria.getDateValeur() != null){
                specification = specification.and(buildStringSpecification(criteria.getDateValeur(),SuiviDelegataire_.dateValeur));
            }
            if (criteria.getValeurContractuel() != null){
                specification = specification.and(buildStringSpecification(criteria.getValeurContractuel(),SuiviDelegataire_.valeurContractuel));
            }
            if (criteria.getValeurConstate() != null){
                specification = specification.and(buildStringSpecification(criteria.getValeurConstate(), SuiviDelegataire_.valeurConstate));
            }
        }
        return specification;
    }
}
