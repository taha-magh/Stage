package ma.gov.marrakech.service;

import io.github.jhipster.service.QueryService;
import ma.gov.marrakech.domain.Delegataire;
import ma.gov.marrakech.domain.PieceJointe;
import ma.gov.marrakech.domain.PieceJointe_;
import ma.gov.marrakech.domain.enumeration.TypePieceJointe;
import ma.gov.marrakech.repository.PieceJointeRepository;
import ma.gov.marrakech.service.criteria.DelegataireCriteria;
import ma.gov.marrakech.service.criteria.PieceJointeCriteria;
import ma.gov.marrakech.service.dto.DelegataireDTO;
import ma.gov.marrakech.service.dto.PieceJointeDTO;
import ma.gov.marrakech.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.metamodel.SingularAttribute;
import java.util.List;

/**
 * Service for executing complex queries for {@link PieceJointe} entities in the database.
 * The main input is a {@link PieceJointeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PieceJointeDTO} or a {@link Page} of {@link PieceJointeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PieceJointeQueryService extends QueryService<PieceJointe> {
    private final Logger log = LoggerFactory.getLogger(PieceJointeQueryService.class);

    private final PieceJointeRepository pieceJointeRepository;

    private final Mapper mapper;
    public PieceJointeQueryService(PieceJointeRepository pieceJointeRepository , Mapper mapper) {
        this.pieceJointeRepository = pieceJointeRepository;
        this.mapper = mapper;

    }

    /**
     * Return a {@link List} of {@link PieceJointeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PieceJointeDTO> findByCriteria(PieceJointeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PieceJointe> specification = createSpecification(criteria);
        return this.mapper.mapList(pieceJointeRepository.findAll(specification), PieceJointeDTO.class);
    }
//    @Transactional(readOnly = true)
//    public List<PieceJointeDTO> findByCriteria(PieceJointeCriteria criteria) {
//        log.debug("find by criteria : {}", criteria);
//        final Specification<PieceJointe> specification = createSpecification(criteria);
//        return pieceJointeRepository.findAll(specification);
//    }

    /**
     * Return a {@link Page} of {@link PieceJointeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
//     * @param pageNumber The page, which should be returned.
//     * @param pageSize The size of the page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PieceJointeDTO> findByCriteria(PieceJointeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PieceJointe> specification = createSpecification(criteria);
        return mapper.mapPageToOther(pieceJointeRepository.findAll(specification, page),PieceJointeDTO.class);
    }
//    @Transactional(readOnly = true)
//    public Page<PieceJointe> findByCriteria(PieceJointeCriteria criteria, int pageNumber, int pageSize) {
//        log.debug("find by criteria : {}, pageNumber: {}, pageSize: {}", criteria, pageNumber, pageSize);
//        final Specification<PieceJointe> specification = createSpecification(criteria);
//        return pieceJointeRepository.findAll(specification, PageRequest.of(pageNumber, pageSize));
//    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PieceJointeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PieceJointe> specification = createSpecification(criteria);
        return pieceJointeRepository.count(specification);
    }

    /**
     * Function to convert {@link PieceJointeCriteria} to a {@link Specification}.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    private Specification<PieceJointe> createSpecification(PieceJointeCriteria criteria) {
        Specification<PieceJointe> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PieceJointe_.id));
            }
//            if (criteria.getTypePieceJointe() != null) {
//                specification = specification.and(buildTypePieceJointeSpecification(criteria.getTypePieceJointe(),PieceJointe_.typePieceJointe));
//            }
//            if (criteria.getFile() != null) {
//                specification = specification.and(buildStringSpecification(criteria.getFile(), PieceJointe_.file));
//            }

            if (criteria.getFileContentType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileContentType(), PieceJointe_.fileContentType));
            }
            if (criteria.getFileName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFileName(), PieceJointe_.fileName));
            }
        }
        return specification;

    }

}
