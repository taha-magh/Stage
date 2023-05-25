package ma.gov.marrakech.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import ma.gov.marrakech.domain.PieceJointe;
import ma.gov.marrakech.repository.PieceJointeRepository;
import ma.gov.marrakech.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link ma.gov.marrakech.domain.PieceJointe}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PieceJointeResource {

  private final Logger log = LoggerFactory.getLogger(PieceJointeResource.class);

  private static final String ENTITY_NAME = "pieceJointe";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final PieceJointeRepository pieceJointeRepository;

  public PieceJointeResource(PieceJointeRepository pieceJointeRepository) {
    this.pieceJointeRepository = pieceJointeRepository;
  }

  /**
   * {@code POST  /piece-jointes} : Create a new pieceJointe.
   *
   * @param pieceJointe the pieceJointe to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pieceJointe, or with status {@code 400 (Bad Request)} if the pieceJointe has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/piece-jointes")
  public ResponseEntity<PieceJointe> createPieceJointe(@Valid @RequestBody PieceJointe pieceJointe)
    throws URISyntaxException {
    log.debug("REST request to save PieceJointe : {}", pieceJointe);
    if (pieceJointe.getId() != null) {
      throw new BadRequestAlertException("A new pieceJointe cannot already have an ID", ENTITY_NAME, "idexists");
    }
    PieceJointe result = pieceJointeRepository.save(pieceJointe);
    return ResponseEntity
      .created(new URI("/api/piece-jointes/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /piece-jointes/:id} : Updates an existing pieceJointe.
   *
   * @param id the id of the pieceJointe to save.
   * @param pieceJointe the pieceJointe to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pieceJointe,
   * or with status {@code 400 (Bad Request)} if the pieceJointe is not valid,
   * or with status {@code 500 (Internal Server Error)} if the pieceJointe couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/piece-jointes/{id}")
  public ResponseEntity<PieceJointe> updatePieceJointe(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody PieceJointe pieceJointe
  ) throws URISyntaxException {
    log.debug("REST request to update PieceJointe : {}, {}", id, pieceJointe);
    if (pieceJointe.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, pieceJointe.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!pieceJointeRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    PieceJointe result = pieceJointeRepository.save(pieceJointe);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pieceJointe.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /piece-jointes/:id} : Partial updates given fields of an existing pieceJointe, field will ignore if it is null
   *
   * @param id the id of the pieceJointe to save.
   * @param pieceJointe the pieceJointe to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pieceJointe,
   * or with status {@code 400 (Bad Request)} if the pieceJointe is not valid,
   * or with status {@code 404 (Not Found)} if the pieceJointe is not found,
   * or with status {@code 500 (Internal Server Error)} if the pieceJointe couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/piece-jointes/{id}", consumes = { "application/json", "application/merge-patch+json" })
  public ResponseEntity<PieceJointe> partialUpdatePieceJointe(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody PieceJointe pieceJointe
  ) throws URISyntaxException {
    log.debug("REST request to partial update PieceJointe partially : {}, {}", id, pieceJointe);
    if (pieceJointe.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, pieceJointe.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!pieceJointeRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<PieceJointe> result = pieceJointeRepository
      .findById(pieceJointe.getId())
      .map(existingPieceJointe -> {
          if (pieceJointe.getTypePieceJointe() != null){
              existingPieceJointe.setTypePieceJointe(pieceJointe.getTypePieceJointe());
          }
          if (pieceJointe.getFile() != null) {
              existingPieceJointe.setFile(pieceJointe.getFile());
          }
          if (pieceJointe.getFileContentType() != null) {
              existingPieceJointe.setFileContentType(pieceJointe.getFileContentType());
          }
          if (pieceJointe.getFileContentType() != null) {
              existingPieceJointe.setFileContentType(pieceJointe.getFileContentType());
          }
          if (pieceJointe.getFileName() != null) {
              existingPieceJointe.setFileName(pieceJointe.getFileName());
          }

        return existingPieceJointe;
      })
      .map(pieceJointeRepository::save);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pieceJointe.getId().toString())
    );
  }

  /**
   * {@code GET  /piece-jointes} : get all the pieceJointeS.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pieceJointeS in body.
   */
  @GetMapping("/piece-jointes")
  public List<PieceJointe> getAllPieceJointeS() {
    log.debug("REST request to get all PieceJointeS");
    return pieceJointeRepository.findAll();
  }

  /**
   * {@code GET  /piece-jointes/:id} : get the "id" pieceJointe.
   *
   * @param id the id of the pieceJointe to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pieceJointe, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/piece-jointes/{id}")
  public ResponseEntity<PieceJointe> getPieceJointe(@PathVariable Long id) {
    log.debug("REST request to get PieceJointe : {}", id);
    Optional<PieceJointe> pieceJointe = pieceJointeRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(pieceJointe);
  }

  /**
   * {@code DELETE  /piece-jointes/:id} : delete the "id" pieceJointe.
   *
   * @param id the id of the pieceJointe to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/piece-jointes/{id}")
  public ResponseEntity<Void> deletePieceJointe(@PathVariable Long id) {
    log.debug("REST request to delete PieceJointe : {}", id);
    pieceJointeRepository.deleteById(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
      .build();
  }
}
