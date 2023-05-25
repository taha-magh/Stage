package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.domain.SuiviSdl;
import ma.gov.marrakech.repository.SuiviSdlRepository;
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
 * REST controller for managing {@link ma.gov.marrakech.domain.SuiviSdl}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SuiviSdlResource {

    private final Logger log = LoggerFactory.getLogger(SuiviSdlResource.class);

    private static final String ENTITY_NAME = "suiviSdl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SuiviSdlRepository suiviSdlRepository;

    public SuiviSdlResource(SuiviSdlRepository suiviSdlRepository) {
        this.suiviSdlRepository = suiviSdlRepository;
    }

    /**
     * {@code POST  /suivi-sdls} : Create a new suiviSdl.
     *
     * @param suiviSdl the suiviSdl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new suiviSdl, or with status {@code 400 (Bad Request)} if the suiviSdl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/suivi-sdls")
    public ResponseEntity<SuiviSdl> createSuiviSdl(@RequestBody SuiviSdl suiviSdl) throws URISyntaxException {
        log.debug("REST request to save SuiviSdl : {}", suiviSdl);
        if (suiviSdl.getId() != null) {
            throw new BadRequestAlertException("A new suiviSdl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuiviSdl result = suiviSdlRepository.save(suiviSdl);
        return ResponseEntity.created(new URI("/api/suivi-sdls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /suivi-sdls} : Updates an existing suiviSdl.
     *
     * @param suiviSdl the suiviSdl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated suiviSdl,
     * or with status {@code 400 (Bad Request)} if the suiviSdl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the suiviSdl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/suivi-sdls")
    public ResponseEntity<SuiviSdl> updateSuiviSdl(@RequestBody SuiviSdl suiviSdl) throws URISyntaxException {
        log.debug("REST request to update SuiviSdl : {}", suiviSdl);
        if (suiviSdl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SuiviSdl result = suiviSdlRepository.save(suiviSdl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, suiviSdl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /suivi-sdls} : get all the suiviSdls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of suiviSdls in body.
     */
    @GetMapping("/suivi-sdls")
    public List<SuiviSdl> getAllSuiviSdls() {
        log.debug("REST request to get all SuiviSdls");
        return suiviSdlRepository.findAll();
    }

    /**
     * {@code GET  /suivi-sdls/:id} : get the "id" suiviSdl.
     *
     * @param id the id of the suiviSdl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the suiviSdl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/suivi-sdls/{id}")
    public ResponseEntity<SuiviSdl> getSuiviSdl(@PathVariable Long id) {
        log.debug("REST request to get SuiviSdl : {}", id);
        Optional<SuiviSdl> suiviSdl = suiviSdlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(suiviSdl);
    }

    /**
     * {@code DELETE  /suivi-sdls/:id} : delete the "id" suiviSdl.
     *
     * @param id the id of the suiviSdl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/suivi-sdls/{id}")
    public ResponseEntity<Void> deleteSuiviSdl(@PathVariable Long id) {
        log.debug("REST request to delete SuiviSdl : {}", id);
        suiviSdlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
