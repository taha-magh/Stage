package ma.gov.marrakech.service;


import io.github.jhipster.service.QueryService;
import ma.gov.marrakech.domain.SuiviSdl;
import ma.gov.marrakech.domain.SuiviSdl;
import ma.gov.marrakech.domain.SuiviSdl_;
import ma.gov.marrakech.repository.SuiviSdlRepository;
import ma.gov.marrakech.service.criteria.SuiviSdlCriteria;
import ma.gov.marrakech.service.criteria.SuiviSdlCriteria;
import ma.gov.marrakech.service.dto.SuiviSdlDTO;
import ma.gov.marrakech.service.dto.SuiviSdlDTO;
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
public class SuiviSdlQueryService extends QueryService<SuiviSdl> {
    private final Logger log = LoggerFactory.getLogger(SuiviSdlQueryService.class);
    private final SuiviSdlRepository suiviSdlRepository;
    private final Mapper mapper;

    public SuiviSdlQueryService(SuiviSdlRepository suiviSdlRepository, Mapper mapper) {
        this.suiviSdlRepository = suiviSdlRepository;
        this.mapper = mapper;
    }

    /**
     *Return a {@link List} of {@link SuiviSdlDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SuiviSdlDTO> findByCriteria(SuiviSdlCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SuiviSdl> specification = createSpecification(criteria);
        return this.mapper.mapList(suiviSdlRepository.findAll(specification), SuiviSdlDTO.class);
    }

    /**
     * Return a {@link Page} of {@link SuiviSdlDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SuiviSdlDTO> findByCriteria(SuiviSdlCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SuiviSdl> specification = createSpecification(criteria);
        return mapper.mapPageToOther(suiviSdlRepository.findAll(specification, page),SuiviSdlDTO.class);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SuiviSdlCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SuiviSdl> specification = createSpecification(criteria);
        return suiviSdlRepository.count(specification);
    }

    /**
     * Function to convert {@link SuiviSdlCriteria} to a {@link Specification}.
     */
    private Specification<SuiviSdl> createSpecification(SuiviSdlCriteria criteria) {
        Specification<SuiviSdl> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SuiviSdl_.id));
            }
            if (criteria.getTypeIndicateur() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeIndicateur(), SuiviSdl_.typeIndicateur));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), SuiviSdl_.nom));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), SuiviSdl_.description));
            }
            if (criteria.getDateValeur() != null){
                specification = specification.and(buildStringSpecification(criteria.getDateValeur(),SuiviSdl_.dateValeur));
            }
            if (criteria.getValeurContractuel() != null){
                specification = specification.and(buildStringSpecification(criteria.getValeurContractuel(),SuiviSdl_.valeurContractuel));
            }
            if (criteria.getValeurConstate() != null){
                specification = specification.and(buildStringSpecification(criteria.getValeurConstate(), SuiviSdl_.valeurConstate));
            }
        }
        return specification;
    }
}

