package ma.gov.marrakech.service;

import io.github.jhipster.service.QueryService;
import ma.gov.marrakech.domain.StructureDelegataire;
import ma.gov.marrakech.domain.StructureDelegataire_;
import ma.gov.marrakech.repository.StructureDelegataireRepository;
import ma.gov.marrakech.service.criteria.StructureDelegataireCriteria;
import ma.gov.marrakech.service.dto.StructureDelegataireDTO;
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
public class StructureDelegataireQueryService extends QueryService<StructureDelegataire> {

    private final Logger log = LoggerFactory.getLogger(StructureDelegataireQueryService.class);

    private final StructureDelegataireRepository structureDelegataireRepository;

    private final Mapper mapper;

    public StructureDelegataireQueryService(StructureDelegataireRepository structureDelegataireRepository, Mapper mapper) {
        this.structureDelegataireRepository = structureDelegataireRepository;
        this.mapper = mapper;
    }

    /**
     * Return a {@link List} of {@link StructureDelegataireDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<StructureDelegataireDTO> findByCriteria(StructureDelegataireCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<StructureDelegataire> specification = createSpecification(criteria);
        return this.mapper.mapList(structureDelegataireRepository.findAll(specification), StructureDelegataireDTO.class);
    }

    /**
     * Return a {@link Page} of {@link StructureDelegataireDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<StructureDelegataireDTO> findByCriteria(StructureDelegataireCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<StructureDelegataire> specification = createSpecification(criteria);
        return mapper.mapPageToOther(structureDelegataireRepository.findAll(specification, page),StructureDelegataireDTO.class);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(StructureDelegataireCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<StructureDelegataire> specification = createSpecification(criteria);
        return structureDelegataireRepository.count(specification);
    }

    /**
     * Function to convert {@link StructureDelegataireCriteria} to a {@link Specification}.
     */
    private Specification<StructureDelegataire> createSpecification(StructureDelegataireCriteria criteria) {
        Specification<StructureDelegataire> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), StructureDelegataire_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), StructureDelegataire_.description));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), StructureDelegataire_.libelle));
            }
        }
        return specification;
    }
}
