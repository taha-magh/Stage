package ma.gov.marrakech.service;

import io.github.jhipster.service.QueryService;
import ma.gov.marrakech.domain.Sdl;
import ma.gov.marrakech.domain.Sdl_;

import ma.gov.marrakech.repository.SdlRepository;
import ma.gov.marrakech.service.criteria.SdlCriteria;
import ma.gov.marrakech.service.dto.SdlDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for executing complex queries for {@link Sdl} entities in the database.
 * The main input is a {@link SdlCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SdlDTO} or a {@link Page} of {@link SdlDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SdlQueryService extends QueryService<Sdl> {

    private final Logger log = LoggerFactory.getLogger(SdlQueryService.class);

    private final SdlRepository sdlRepository;

    private final Mapper mapper;

    public SdlQueryService(SdlRepository sdlRepository, Mapper mapper) {
        this.sdlRepository = sdlRepository;
        this.mapper = mapper;
    }

    /**
     * Return a {@link List} of {@link SdlDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SdlDTO> findByCriteria(SdlCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Sdl> specification = createSpecification(criteria);
        return this.mapper.mapList(sdlRepository.findAll(specification), SdlDTO.class);
    }

    /**
     * Return a {@link Page} of {@link SdlDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */

    @Transactional(readOnly = true)
    public Page<SdlDTO> findByCriteria(SdlCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Sdl> specification = createSpecification(criteria);
        return mapper.mapPageToOther(sdlRepository.findAll(specification, page),SdlDTO.class);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SdlCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Sdl> specification = createSpecification(criteria);
        return sdlRepository.count(specification);
    }

    /**
     * Function to convert {@link SdlCriteria} to a {@link Specification}.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    private Specification<Sdl> createSpecification(SdlCriteria criteria) {
        Specification<Sdl> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Sdl_.id));
            }
            if (criteria.getRaisonSocial() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisonSocial(), Sdl_.raisonSocial));
            }
            if (criteria.getRaisoCom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRaisoCom(), Sdl_.raisonCom));
            }
            if (criteria.getIdFiscale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdFiscale(), Sdl_.idFiscale));
            }
            if (criteria.getIce() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIce(), Sdl_.ice));
            }
            if (criteria.getNumCnss() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumCnss(), Sdl_.numCnss));
            }
            if (criteria.getAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdresse(), Sdl_.adresse));
            }
            if (criteria.getTel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTel(), Sdl_.tel));
            }
            if (criteria.getFax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFax(), Sdl_.fax));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Sdl_.email));
            }
            if (criteria.getGerant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGerant(), Sdl_.gerant));
            }
            if (criteria.getStructure() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStructure(), Sdl_.structure));
            }
            if (criteria.getCapital() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCapital(), Sdl_.capital));
            }
        }
        return specification;
    }
}




