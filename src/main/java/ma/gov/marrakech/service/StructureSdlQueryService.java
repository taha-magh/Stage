package ma.gov.marrakech.service;

import io.github.jhipster.service.QueryService;
import ma.gov.marrakech.domain.StructureSdl;
import ma.gov.marrakech.domain.StructureSdl;
import ma.gov.marrakech.domain.StructureSdl_;
import ma.gov.marrakech.repository.StructureSdlRepository;
import ma.gov.marrakech.service.criteria.StructureSdlCriteria;
import ma.gov.marrakech.service.criteria.StructureSdlCriteria;
import ma.gov.marrakech.service.dto.StructureSdlDTO;
import ma.gov.marrakech.service.dto.StructureSdlDTO;
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
public class StructureSdlQueryService extends QueryService<StructureSdl> {

    private final Logger log = LoggerFactory.getLogger(StructureSdlQueryService.class);

    private final StructureSdlRepository structureSdlRepository;

    private final Mapper mapper;

    public StructureSdlQueryService(StructureSdlRepository structureSdlRepository, Mapper mapper) {
        this.structureSdlRepository = structureSdlRepository;
        this.mapper = mapper;
    }

    /**
     * Return a {@link List} of {@link StructureSdlDTO} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<StructureSdlDTO> findByCriteria(StructureSdlCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<StructureSdl> specification = createSpecification(criteria);
        return this.mapper.mapList(structureSdlRepository.findAll(specification), StructureSdlDTO.class);
    }

    /**
     * Return a {@link Page} of {@link StructureSdlDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<StructureSdlDTO> findByCriteria(StructureSdlCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<StructureSdl> specification = createSpecification(criteria);
        return mapper.mapPageToOther(structureSdlRepository.findAll(specification, page),StructureSdlDTO.class);
    }

    /**
     * Return the number of matching entities in the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(StructureSdlCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<StructureSdl> specification = createSpecification(criteria);
        return structureSdlRepository.count(specification);
    }

    /**
     * Function to convert {@link StructureSdlCriteria} to a {@link Specification}.
     */
    private Specification<StructureSdl> createSpecification(StructureSdlCriteria criteria) {
        Specification<StructureSdl> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), StructureSdl_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), StructureSdl_.description));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), StructureSdl_.libelle));
            }
        }
        return specification;
    }
}
