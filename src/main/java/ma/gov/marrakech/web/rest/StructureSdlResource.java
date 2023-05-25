package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.domain.StructureSdl;
import ma.gov.marrakech.repository.StructureSdlRepository;
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
 * REST controller for managing {@link ma.gov.marrakech.domain.StructureSdl}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StructureSdlResource {

    private final Logger log = LoggerFactory.getLogger(StructureSdlResource.class);

    private static final String ENTITY_NAME = "structureSdl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StructureSdlRepository structureSdlRepository;

    public StructureSdlResource(StructureSdlRepository structureSdlRepository) {
        this.structureSdlRepository = structureSdlRepository;
    }

    /**
     * {@code POST  /structure-sdls} : Create a new structureSdl.
     *
     * @param structureSdl the structureSdl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new structureSdl, or with status {@code 400 (Bad Request)} if the structureSdl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/structure-sdls")
    public ResponseEntity<StructureSdl> createStructureSdl(@RequestBody StructureSdl structureSdl) throws URISyntaxException {
        log.debug("REST request to save StructureSdl : {}", structureSdl);
        if (structureSdl.getId() != null) {
            throw new BadRequestAlertException("A new structureSdl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StructureSdl result = structureSdlRepository.save(structureSdl);
        return ResponseEntity.created(new URI("/api/structure-sdls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /structure-sdls} : Updates an existing structureSdl.
     *
     * @param structureSdl the structureSdl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated structureSdl,
     * or with status {@code 400 (Bad Request)} if the structureSdl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the structureSdl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/structure-sdls")
    public ResponseEntity<StructureSdl> updateStructureSdl(@RequestBody StructureSdl structureSdl) throws URISyntaxException {
        log.debug("REST request to update StructureSdl : {}", structureSdl);
        if (structureSdl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StructureSdl result = structureSdlRepository.save(structureSdl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, structureSdl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /structure-sdls} : get all the structureSdls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of structureSdls in body.
     */
    @GetMapping("/structure-sdls")
    public List<StructureSdl> getAllStructureSdls() {
        log.debug("REST request to get all StructureSdls");
        return structureSdlRepository.findAll();
    }

    /**
     * {@code GET  /structure-sdls/:id} : get the "id" structureSdl.
     *
     * @param id the id of the structureSdl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the structureSdl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/structure-sdls/{id}")
    public ResponseEntity<StructureSdl> getStructureSdl(@PathVariable Long id) {
        log.debug("REST request to get StructureSdl : {}", id);
        Optional<StructureSdl> structureSdl = structureSdlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(structureSdl);
    }

    /**
     * {@code DELETE  /structure-sdls/:id} : delete the "id" structureSdl.
     *
     * @param id the id of the structureSdl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/structure-sdls/{id}")
    public ResponseEntity<Void> deleteStructureSdl(@PathVariable Long id) {
        log.debug("REST request to delete StructureSdl : {}", id);
        structureSdlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
