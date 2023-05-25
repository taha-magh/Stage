package ma.gov.marrakech.service;

import java.util.List;

import javax.persistence.criteria.JoinType;


import ma.gov.marrakech.domain.Delegataire;
import ma.gov.marrakech.domain.Delegataire_;
import ma.gov.marrakech.repository.DelegataireRepository;
import ma.gov.marrakech.service.criteria.DelegataireCriteria;
import ma.gov.marrakech.service.dto.DelegataireDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Delegataire} entities in the database.
 * The main input is a {@link DelegataireCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DelegataireDTO} or a {@link Page} of {@link DelegataireDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DelegataireQueryService extends QueryService<Delegataire> {

    private final Logger log = LoggerFactory.getLogger(DelegataireQueryService.class);

    private final DelegataireRepository delegataireRepository;

    private final Mapper mapper;

    public DelegataireQueryService(DelegataireRepository delegataireRepository, Mapper mapper) {
        this.delegataireRepository = delegataireRepository;
        this.mapper = mapper;
    }

    /**
     * Return a {@link List} of {@link DelegataireDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DelegataireDTO> findByCriteria(DelegataireCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Delegataire> specification = createSpecification(criteria);
        return this.mapper.mapList(delegataireRepository.findAll(specification), DelegataireDTO.class);
    }

    /**
     * Return a {@link Page} of {@link DelegataireDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */

    @Transactional(readOnly = true)
    public Page<DelegataireDTO> findByCriteria(DelegataireCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Delegataire> specification = createSpecification(criteria);
        return mapper.mapPageToOther(delegataireRepository.findAll(specification, page),DelegataireDTO.class);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DelegataireCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Delegataire> specification = createSpecification(criteria);
        return delegataireRepository.count(specification);
    }

    /**
     * Function to convert {@link DelegataireCriteria} to a {@link Specification}.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    private Specification<Delegataire> createSpecification(DelegataireCriteria criteria) {
        Specification<Delegataire> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Delegataire_.id));
            }
            if (criteria.getRaisonSocial() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSocial(), Delegataire_.raisonSocial));
            }
            if (criteria.getRaisoCom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisoCom(), Delegataire_.raisonCom));
            }
            if (criteria.getIdFiscale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdFiscale(), Delegataire_.idFiscale));
            }
            if (criteria.getIce() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIce(), Delegataire_.ice));
            }
            if (criteria.getNumCnss() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumCnss(), Delegataire_.numCnss));
            }
            if (criteria.getAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdresse(), Delegataire_.adresse));
            }
            if (criteria.getTel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTel(), Delegataire_.tel));
            }
            if (criteria.getFax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFax(), Delegataire_.fax));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Delegataire_.email));
            }
            if (criteria.getGerant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGerant(), Delegataire_.gerant));
            }
            if (criteria.getStructure() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStructure(), Delegataire_.structure));
            }
            if (criteria.getCapital() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCapital(), Delegataire_.capital));
            }
        }
        return specification;
    }
}


