package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.domain.TypeIndicDelegataire;
import ma.gov.marrakech.repository.TypeIndicDelegataireRepository;
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
 * REST controller for managing {@link ma.gov.marrakech.domain.TypeIndicDelegataire}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TypeIndicDelegataireResource {

    private final Logger log = LoggerFactory.getLogger(TypeIndicDelegataireResource.class);

    private static final String ENTITY_NAME = "typeIndicDelegataire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeIndicDelegataireRepository typeIndicDelegataireRepository;

    public TypeIndicDelegataireResource(TypeIndicDelegataireRepository typeIndicDelegataireRepository) {
        this.typeIndicDelegataireRepository = typeIndicDelegataireRepository;
    }

    /**
     * {@code POST  /type-indic-delegataires} : Create a new typeIndicDelegataire.
     *
     * @param typeIndicDelegataire the typeIndicDelegataire to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeIndicDelegataire, or with status {@code 400 (Bad Request)} if the typeIndicDelegataire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-indic-delegataires")
    public ResponseEntity<TypeIndicDelegataire> createTypeIndicDelegataire(@RequestBody TypeIndicDelegataire typeIndicDelegataire) throws URISyntaxException {
        log.debug("REST request to save TypeIndicDelegataire : {}", typeIndicDelegataire);
        if (typeIndicDelegataire.getId() != null) {
            throw new BadRequestAlertException("A new typeIndicDelegataire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeIndicDelegataire result = typeIndicDelegataireRepository.save(typeIndicDelegataire);
        return ResponseEntity.created(new URI("/api/type-indic-delegataires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-indic-delegataires} : Updates an existing typeIndicDelegataire.
     *
     * @param typeIndicDelegataire the typeIndicDelegataire to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeIndicDelegataire,
     * or with status {@code 400 (Bad Request)} if the typeIndicDelegataire is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeIndicDelegataire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-indic-delegataires")
    public ResponseEntity<TypeIndicDelegataire> updateTypeIndicDelegataire(@RequestBody TypeIndicDelegataire typeIndicDelegataire) throws URISyntaxException {
        log.debug("REST request to update TypeIndicDelegataire : {}", typeIndicDelegataire);
        if (typeIndicDelegataire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeIndicDelegataire result = typeIndicDelegataireRepository.save(typeIndicDelegataire);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, typeIndicDelegataire.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-indic-delegataires} : get all the typeIndicDelegataires.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeIndicDelegataires in body.
     */
    @GetMapping("/type-indic-delegataires")
    public List<TypeIndicDelegataire> getAllTypeIndicDelegataires() {
        log.debug("REST request to get all TypeIndicDelegataires");
        return typeIndicDelegataireRepository.findAll();
    }

    /**
     * {@code GET  /type-indic-delegataires/:id} : get the "id" typeIndicDelegataire.
     *
     * @param id the id of the typeIndicDelegataire to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeIndicDelegataire, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-indic-delegataires/{id}")
    public ResponseEntity<TypeIndicDelegataire> getTypeIndicDelegataire(@PathVariable Long id) {
        log.debug("REST request to get TypeIndicDelegataire : {}", id);
        Optional<TypeIndicDelegataire> typeIndicDelegataire = typeIndicDelegataireRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(typeIndicDelegataire);
    }

    /**
     * {@code DELETE  /type-indic-delegataires/:id} : delete the "id" typeIndicDelegataire.
     *
     * @param id the id of the typeIndicDelegataire to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-indic-delegataires/{id}")
    public ResponseEntity<Void> deleteTypeIndicDelegataire(@PathVariable Long id) {
        log.debug("REST request to delete TypeIndicDelegataire : {}", id);
        typeIndicDelegataireRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
