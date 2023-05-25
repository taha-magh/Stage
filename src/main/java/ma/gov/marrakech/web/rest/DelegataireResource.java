package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.domain.Delegataire;
import ma.gov.marrakech.repository.DelegataireRepository;
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
 * REST controller for managing {@link ma.gov.marrakech.domain.Delegataire}.
 */
@RestController
@RequestMapping("/api")
@Transactional
@CrossOrigin(origins = "http://localhost:4200/")
public class DelegataireResource {

    private final Logger log = LoggerFactory.getLogger(DelegataireResource.class);

    private static final String ENTITY_NAME = "delegataire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DelegataireRepository delegataireRepository;

    public DelegataireResource(DelegataireRepository delegataireRepository) {
        this.delegataireRepository = delegataireRepository;
    }

    /**
     * {@code POST  /delegataires} : Create a new delegataire.
     *
     * @param delegataire the delegataire to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new delegataire, or with status {@code 400 (Bad Request)} if the delegataire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/delegataires")
    public ResponseEntity<Delegataire> createDelegataire(@RequestBody Delegataire delegataire) throws URISyntaxException {
        log.debug("REST request to save Delegataire : {}", delegataire);
        if (delegataire.getId() != null) {
            throw new BadRequestAlertException("A new delegataire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Delegataire result = delegataireRepository.save(delegataire);
        return ResponseEntity.created(new URI("/api/delegataires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /delegataires} : Updates an existing delegataire.
     *
     * @param delegataire the delegataire to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated delegataire,
     * or with status {@code 400 (Bad Request)} if the delegataire is not valid,
     * or with status {@code 500 (Internal Server Error)} if the delegataire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/delegataires")
    public ResponseEntity<Delegataire> updateDelegataire(@RequestBody Delegataire delegataire) throws URISyntaxException {
        log.debug("REST request to update Delegataire : {}", delegataire);
        if (delegataire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Delegataire result = delegataireRepository.save(delegataire);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, delegataire.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /delegataires} : get all the delegataires.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of delegataires in body.
     */

    @GetMapping("/delegataires")
    public List<Delegataire> getAllDelegataires() {
        log.debug("REST request to get all Delegataires");
        return delegataireRepository.findAll();
    }

    /**
     * {@code GET  /delegataires/:id} : get the "id" delegataire.
     *
     * @param id the id of the delegataire to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the delegataire, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/delegataires/{id}")
    public ResponseEntity<Delegataire> getDelegataire(@PathVariable Long id) {
        log.debug("REST request to get Delegataire : {}", id);
        Optional<Delegataire> delegataire = delegataireRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(delegataire);
    }

    /**
     * {@code DELETE  /delegataires/:id} : delete the "id" delegataire.
     *
     * @param id the id of the delegataire to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delegataires/{id}")
    public ResponseEntity<Void> deleteDelegataire(@PathVariable Long id) {
        log.debug("REST request to delete Delegataire : {}", id);
        delegataireRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
