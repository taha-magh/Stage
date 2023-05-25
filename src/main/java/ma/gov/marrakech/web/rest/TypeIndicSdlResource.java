package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.domain.TypeIndicSdl;
import ma.gov.marrakech.repository.TypeIndicSdlRepository;
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
 * REST controller for managing {@link ma.gov.marrakech.domain.TypeIndicSdl}.
 */
@RestController
@RequestMapping("api")
@Transactional
public class TypeIndicSdlResource {

    private final Logger log = LoggerFactory.getLogger(TypeIndicSdlResource.class);

    private static final String ENTITY_NAME = "typeIndicSdl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeIndicSdlRepository typeIndicSdlRepository;

    public TypeIndicSdlResource(TypeIndicSdlRepository typeIndicSdlRepository) {
        this.typeIndicSdlRepository = typeIndicSdlRepository;
    }

    /**
     * {@code POST  /type-indic-sdls} : Create a new typeIndicSdl.
     *
     * @param typeIndicSdl the typeIndicSdl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeIndicSdl, or with status {@code 400 (Bad Request)} if the typeIndicSdl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-indic-sdls")
    public ResponseEntity<TypeIndicSdl> createTypeIndicSdl(@RequestBody TypeIndicSdl typeIndicSdl) throws URISyntaxException {
        log.debug("REST request to save TypeIndicSdl : {}", typeIndicSdl);
        if (typeIndicSdl.getId() != null) {
            throw new BadRequestAlertException("A new typeIndicSdl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeIndicSdl result = typeIndicSdlRepository.save(typeIndicSdl);
        return ResponseEntity.created(new URI("/api/type-indic-sdls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-indic-sdls} : Updates an existing typeIndicSdl.
     *
     * @param typeIndicSdl the typeIndicSdl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeIndicSdl,
     * or with status {@code 400 (Bad Request)} if the typeIndicSdl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeIndicSdl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-indic-sdls")
    public ResponseEntity<TypeIndicSdl> updateTypeIndicSdl(@RequestBody TypeIndicSdl typeIndicSdl) throws URISyntaxException {
        log.debug("REST request to update TypeIndicSdl : {}", typeIndicSdl);
        if (typeIndicSdl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeIndicSdl result = typeIndicSdlRepository.save(typeIndicSdl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, typeIndicSdl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-indic-sdls} : get all the typeIndicSdls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeIndicSdls in body.
     */
    @GetMapping("/type-indic-sdls")
    public List<TypeIndicSdl> getAllTypeIndicSdls() {
        log.debug("REST request to get all TypeIndicSdls");
        return typeIndicSdlRepository.findAll();
    }

    /**
     * {@code GET  /type-indic-sdls/:id} : get the "id" typeIndicSdl.
     *
     * @param id the id of the typeIndicSdl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeIndicSdl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-indic-sdls/{id}")
    public ResponseEntity<TypeIndicSdl> getTypeIndicSdl(@PathVariable Long id) {
        log.debug("REST request to get TypeIndicSdl : {}", id);
        Optional<TypeIndicSdl> typeIndicSdl = typeIndicSdlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(typeIndicSdl);
    }

    /**
     * {@code DELETE  /type-indic-sdls/:id} : delete the "id" typeIndicSdl.
     *
     * @param id the id of the typeIndicSdl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-indic-sdls/{id}")
    public ResponseEntity<Void> deleteTypeIndicSdl(@PathVariable Long id) {
        log.debug("REST request to delete TypeIndicSdl : {}", id);
        typeIndicSdlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
