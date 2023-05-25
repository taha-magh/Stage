package ma.gov.marrakech.service;

import io.github.jhipster.service.QueryService;
import ma.gov.marrakech.domain.TypeIndicDelegataire;
import ma.gov.marrakech.domain.TypeIndicDelegataire;
import ma.gov.marrakech.domain.TypeIndicDelegataire;
import ma.gov.marrakech.domain.TypeIndicDelegataire_;
import ma.gov.marrakech.repository.TypeIndicDelegataireRepository;
import ma.gov.marrakech.service.criteria.TypeIndicDelegataireCriteria;
import ma.gov.marrakech.service.criteria.TypeIndicDelegataireCriteria;
import ma.gov.marrakech.service.criteria.TypeIndicDelegataireCriteria;
import ma.gov.marrakech.service.dto.TypeIndicDelegataireDTO;
import ma.gov.marrakech.service.dto.TypeIndicDelegataireDTO;
import ma.gov.marrakech.service.dto.TypeIndicDelegataireDTO;
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
public class TypeIndicDelegataireQueryService extends QueryService<TypeIndicDelegataire> {
    private final Logger log = LoggerFactory.getLogger(TypeIndicDelegataireQueryService.class);
    private final TypeIndicDelegataireRepository typeIndicDelegataireRepository;
    private final Mapper mapper;

    public TypeIndicDelegataireQueryService(TypeIndicDelegataireRepository typeIndicDelegataireRepository, Mapper mapper) {
        this.typeIndicDelegataireRepository = typeIndicDelegataireRepository;
        this.mapper = mapper;
    }

    /**
     * Return a {@link List} of {@link TypeIndicDelegataireDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TypeIndicDelegataireDTO> findByCriteria(TypeIndicDelegataireCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TypeIndicDelegataire> specification = createSpecification(criteria);
        return this.mapper.mapList(typeIndicDelegataireRepository.findAll(specification), TypeIndicDelegataireDTO.class);
    }

    /**
     * Return a {@link Page} of {@link TypeIndicDelegataireDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page   The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TypeIndicDelegataireDTO> findByCriteria(TypeIndicDelegataireCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TypeIndicDelegataire> specification = createSpecification(criteria);
        return mapper.mapPageToOther(typeIndicDelegataireRepository.findAll(specification, page),TypeIndicDelegataireDTO.class);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TypeIndicDelegataireCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TypeIndicDelegataire> specification = createSpecification(criteria);
        return typeIndicDelegataireRepository.count(specification);
    }

    /**
     * Function to convert {@link TypeIndicDelegataireCriteria} to a {@link Specification}.
     */
    private Specification<TypeIndicDelegataire> createSpecification(TypeIndicDelegataireCriteria criteria) {
        Specification<TypeIndicDelegataire> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TypeIndicDelegataire_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TypeIndicDelegataire_.description));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), TypeIndicDelegataire_.libelle));
            }
        }
            return specification;
    }
}
