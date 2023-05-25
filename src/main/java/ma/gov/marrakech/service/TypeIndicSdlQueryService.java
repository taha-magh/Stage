package ma.gov.marrakech.service;


import io.github.jhipster.service.QueryService;
import ma.gov.marrakech.domain.TypeIndicSdl;
import ma.gov.marrakech.domain.TypeIndicSdl;
import ma.gov.marrakech.domain.TypeIndicSdl_;
import ma.gov.marrakech.repository.TypeIndicSdlRepository;
import ma.gov.marrakech.service.criteria.TypeIndicSdlCriteria;
import ma.gov.marrakech.service.criteria.TypeIndicSdlCriteria;
import ma.gov.marrakech.service.dto.TypeIndicSdlDTO;
import ma.gov.marrakech.service.dto.TypeIndicSdlDTO;
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
public class TypeIndicSdlQueryService extends QueryService<TypeIndicSdl> {
    private final Logger log = LoggerFactory.getLogger(TypeIndicSdlQueryService.class);
    private final TypeIndicSdlRepository typeIndicSdlRepository;
    private final Mapper mapper;

    public TypeIndicSdlQueryService(TypeIndicSdlRepository typeIndicSdlRepository, Mapper mapper) {
        this.typeIndicSdlRepository = typeIndicSdlRepository;
        this.mapper = mapper;
    }
    /**
     * Return a {@link List} of {@link TypeIndicSdlDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TypeIndicSdlDTO> findByCriteria(TypeIndicSdlCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TypeIndicSdl> specification = createSpecification(criteria);
        return this.mapper.mapList(typeIndicSdlRepository.findAll(specification), TypeIndicSdlDTO.class);
    }

    /**
     * Return a {@link Page} of {@link TypeIndicSdlDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page   The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeIndicSdlDTO> findByCriteria(TypeIndicSdlCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TypeIndicSdl> specification = createSpecification(criteria);
        return mapper.mapPageToOther(typeIndicSdlRepository.findAll(specification, page),TypeIndicSdlDTO.class);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TypeIndicSdlCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TypeIndicSdl> specification = createSpecification(criteria);
        return typeIndicSdlRepository.count(specification);
    }

    /**
     * Function to convert {@link TypeIndicSdlCriteria} to a {@link Specification}.
     */
    private Specification<TypeIndicSdl> createSpecification(TypeIndicSdlCriteria criteria) {
        Specification<TypeIndicSdl> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TypeIndicSdl_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TypeIndicSdl_.description));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), TypeIndicSdl_.libelle));
            }
        }
        return specification;
    }
}


