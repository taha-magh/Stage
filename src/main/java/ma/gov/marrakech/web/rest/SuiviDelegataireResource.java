package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.domain.SuiviDelegataire;
import ma.gov.marrakech.repository.SuiviDelegataireRepository;
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
 * REST controller for managing {@link ma.gov.marrakech.domain.SuiviDelegataire}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SuiviDelegataireResource {

    private final Logger log = LoggerFactory.getLogger(SuiviDelegataireResource.class);

    private static final String ENTITY_NAME = "suiviDelegataire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SuiviDelegataireRepository suiviDelegataireRepository;

    public SuiviDelegataireResource(SuiviDelegataireRepository suiviDelegataireRepository) {
        this.suiviDelegataireRepository = suiviDelegataireRepository;
    }

    /**
     * {@code POST  /suivi-delegataires} : Create a new suiviDelegataire.
     *
     * @param suiviDelegataire the suiviDelegataire to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new suiviDelegataire, or with status {@code 400 (Bad Request)} if the suiviDelegataire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/suivi-delegataires")
    public ResponseEntity<SuiviDelegataire> createSuiviDelegataire(@RequestBody SuiviDelegataire suiviDelegataire) throws URISyntaxException {
        log.debug("REST request to save SuiviDelegataire : {}", suiviDelegataire);
        if (suiviDelegataire.getId() != null) {
            throw new BadRequestAlertException("A new suiviDelegataire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuiviDelegataire result = suiviDelegataireRepository.save(suiviDelegataire);
        return ResponseEntity.created(new URI("/api/suivi-delegataires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /suivi-delegataires} : Updates an existing suiviDelegataire.
     *
     * @param suiviDelegataire the suiviDelegataire to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated suiviDelegataire,
     * or with status {@code 400 (Bad Request)} if the suiviDelegataire is not valid,
     * or with status {@code 500 (Internal Server Error)} if the suiviDelegataire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/suivi-delegataires")
    public ResponseEntity<SuiviDelegataire> updateSuiviDelegataire(@RequestBody SuiviDelegataire suiviDelegataire) throws URISyntaxException {
        log.debug("REST request to update SuiviDelegataire : {}", suiviDelegataire);
        if (suiviDelegataire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SuiviDelegataire result = suiviDelegataireRepository.save(suiviDelegataire);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, suiviDelegataire.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /suivi-delegataires} : get all the suiviDelegataires.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of suiviDelegataires in body.
     */
    @GetMapping("/suivi-delegataires")
    public List<SuiviDelegataire> getAllSuiviDelegataires() {
        log.debug("REST request to get all SuiviDelegataires");
        return suiviDelegataireRepository.findAll();
    }

    /**
     * {@code GET  /suivi-delegataires/:id} : get the "id" suiviDelegataire.
     *
     * @param id the id of the suiviDelegataire to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the suiviDelegataire, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/suivi-delegataires/{id}")
    public ResponseEntity<SuiviDelegataire> getSuiviDelegataire(@PathVariable Long id) {
        log.debug("REST request to get SuiviDelegataire : {}", id);
        Optional<SuiviDelegataire> suiviDelegataire = suiviDelegataireRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(suiviDelegataire);
    }

    /**
     * {@code DELETE  /suivi-delegataires/:id} : delete the "id" suiviDelegataire.
     *
     * @param id the id of the suiviDelegataire to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/suivi-delegataires/{id}")
    public ResponseEntity<Void> deleteSuiviDelegataire(@PathVariable Long id) {
        log.debug("REST request to delete SuiviDelegataire : {}", id);
        suiviDelegataireRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
