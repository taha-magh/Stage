package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.domain.StructureDelegataire;
import ma.gov.marrakech.repository.StructureDelegataireRepository;
import ma.gov.marrakech.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ma.gov.marrakech.domain.StructureDelegataire}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StructureDelegataireResource {

    private final Logger log = LoggerFactory.getLogger(StructureDelegataireResource.class);

    private static final String ENTITY_NAME = "structureDelegataire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StructureDelegataireRepository structureDelegataireRepository;

    public StructureDelegataireResource(StructureDelegataireRepository structureDelegataireRepository) {
        this.structureDelegataireRepository = structureDelegataireRepository;
    }

    /**
     * {@code POST  /structure-delegataires} : Create a new structureDelegataire.
     *
     * @param structureDelegataire the structureDelegataire to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new structureDelegataire, or with status {@code 400 (Bad Request)} if the structureDelegataire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/structure-delegataires")
    public ResponseEntity<StructureDelegataire> createStructureDelegataire(@RequestBody StructureDelegataire structureDelegataire) throws URISyntaxException {
        log.debug("REST request to save StructureDelegataire : {}", structureDelegataire);
        if (structureDelegataire.getId() != null) {
            throw new BadRequestAlertException("A new structureDelegataire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StructureDelegataire result = structureDelegataireRepository.save(structureDelegataire);
        return ResponseEntity.created(new URI("/api/structure-delegataires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /structure-delegataires} : Updates an existing structureDelegataire.
     *
     * @param structureDelegataire the structureDelegataire to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated structureDelegataire,
     * or with status {@code 400 (Bad Request)} if the structureDelegataire is not valid,
     * or with status {@code 500 (Internal Server Error)} if the structureDelegataire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/structure-delegataires")
    public ResponseEntity<StructureDelegataire> updateStructureDelegataire(@RequestBody StructureDelegataire structureDelegataire) throws URISyntaxException {
        log.debug("REST request to update StructureDelegataire : {}", structureDelegataire);
        if (structureDelegataire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StructureDelegataire result = structureDelegataireRepository.save(structureDelegataire);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, structureDelegataire.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /structure-delegataires} : get all the structureDelegataires.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of structureDelegataires in body.
     */
    @GetMapping("/structure-delegataires")
    public List<StructureDelegataire> getAllStructureDelegataires() {
        log.debug("REST request to get all StructureDelegataires");
        return structureDelegataireRepository.findAll();
    }

    /**
     * {@code GET  /structure-delegataires/:id} : get the "id" structureDelegataire.
     *
     * @param id the id of the structureDelegataire to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the structureDelegataire, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/structure-delegataires/{id}")
    public ResponseEntity<StructureDelegataire> getStructureDelegataire(@PathVariable Long id) {
        log.debug("REST request to get StructureDelegataire : {}", id);
        Optional<StructureDelegataire> structureDelegataire = structureDelegataireRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(structureDelegataire);
    }

    /**
     * {@code DELETE  /structure-delegataires/:id} : delete the "id" structureDelegataire.
     *
     * @param id the id of the structureDelegataire to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/structure-delegataires/{id}")
    public ResponseEntity<Void> deleteStructureDelegataire(@PathVariable Long id) {
        log.debug("REST request to delete StructureDelegataire : {}", id);
        structureDelegataireRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
