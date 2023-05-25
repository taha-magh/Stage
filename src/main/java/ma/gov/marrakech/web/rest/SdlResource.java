package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.domain.Sdl;
import ma.gov.marrakech.repository.SdlRepository;
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
 * REST controller for managing {@link ma.gov.marrakech.domain.Sdl}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SdlResource {

    private final Logger log = LoggerFactory.getLogger(SdlResource.class);

    private static final String ENTITY_NAME = "sdl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SdlRepository sdlRepository;

    public SdlResource(SdlRepository sdlRepository) {
        this.sdlRepository = sdlRepository;
    }

    /**
     * {@code POST  /sdls} : Create a new sdl.
     *
     * @param sdl the sdl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sdl, or with status {@code 400 (Bad Request)} if the sdl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sdls")
    public ResponseEntity<Sdl> createSdl(@RequestBody Sdl sdl) throws URISyntaxException {
        log.debug("REST request to save Sdl : {}", sdl);
        if (sdl.getId() != null) {
            throw new BadRequestAlertException("A new sdl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sdl result = sdlRepository.save(sdl);
        return ResponseEntity.created(new URI("/api/sdls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sdls} : Updates an existing sdl.
     *
     * @param sdl the sdl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sdl,
     * or with status {@code 400 (Bad Request)} if the sdl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sdl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sdls")
    public ResponseEntity<Sdl> updateSdl(@RequestBody Sdl sdl) throws URISyntaxException {
        log.debug("REST request to update Sdl : {}", sdl);
        if (sdl.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Sdl result = sdlRepository.save(sdl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sdl.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sdls} : get all the sdls.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sdls in body.
     */
    @GetMapping("/sdls")
    public List<Sdl> getAllSdls() {
        log.debug("REST request to get all Sdls");
        return sdlRepository.findAll();
    }

    /**
     * {@code GET  /sdls/:id} : get the "id" sdl.
     *
     * @param id the id of the sdl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sdl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sdls/{id}")
    public ResponseEntity<Sdl> getSdl(@PathVariable Long id) {
        log.debug("REST request to get Sdl : {}", id);
        Optional<Sdl> sdl = sdlRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sdl);
    }

    /**
     * {@code DELETE  /sdls/:id} : delete the "id" sdl.
     *
     * @param id the id of the sdl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sdls/{id}")
    public ResponseEntity<Void> deleteSdl(@PathVariable Long id) {
        log.debug("REST request to delete Sdl : {}", id);
        sdlRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
