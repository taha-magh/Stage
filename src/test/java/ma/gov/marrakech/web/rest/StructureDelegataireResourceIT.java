package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.MarrakechBackApp;
import ma.gov.marrakech.domain.StructureDelegataire;
import ma.gov.marrakech.repository.StructureDelegataireRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link StructureDelegataireResource} REST controller.
 */
@SpringBootTest(classes = MarrakechBackApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StructureDelegataireResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private StructureDelegataireRepository structureDelegataireRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStructureDelegataireMockMvc;

    private StructureDelegataire structureDelegataire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StructureDelegataire createEntity(EntityManager em) {
        StructureDelegataire structureDelegataire = new StructureDelegataire()
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION);
        return structureDelegataire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StructureDelegataire createUpdatedEntity(EntityManager em) {
        StructureDelegataire structureDelegataire = new StructureDelegataire()
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);
        return structureDelegataire;
    }

    @BeforeEach
    public void initTest() {
        structureDelegataire = createEntity(em);
    }

    @Test
    @Transactional
    public void createStructureDelegataire() throws Exception {
        int databaseSizeBeforeCreate = structureDelegataireRepository.findAll().size();
        // Create the StructureDelegataire
        restStructureDelegataireMockMvc.perform(post("/api/structure-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(structureDelegataire)))
            .andExpect(status().isCreated());

        // Validate the StructureDelegataire in the database
        List<StructureDelegataire> structureDelegataireList = structureDelegataireRepository.findAll();
        assertThat(structureDelegataireList).hasSize(databaseSizeBeforeCreate + 1);
        StructureDelegataire testStructureDelegataire = structureDelegataireList.get(structureDelegataireList.size() - 1);
        assertThat(testStructureDelegataire.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testStructureDelegataire.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createStructureDelegataireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = structureDelegataireRepository.findAll().size();

        // Create the StructureDelegataire with an existing ID
        structureDelegataire.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStructureDelegataireMockMvc.perform(post("/api/structure-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(structureDelegataire)))
            .andExpect(status().isBadRequest());

        // Validate the StructureDelegataire in the database
        List<StructureDelegataire> structureDelegataireList = structureDelegataireRepository.findAll();
        assertThat(structureDelegataireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStructureDelegataires() throws Exception {
        // Initialize the database
        structureDelegataireRepository.saveAndFlush(structureDelegataire);

        // Get all the structureDelegataireList
        restStructureDelegataireMockMvc.perform(get("/api/structure-delegataires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(structureDelegataire.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getStructureDelegataire() throws Exception {
        // Initialize the database
        structureDelegataireRepository.saveAndFlush(structureDelegataire);

        // Get the structureDelegataire
        restStructureDelegataireMockMvc.perform(get("/api/structure-delegataires/{id}", structureDelegataire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(structureDelegataire.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingStructureDelegataire() throws Exception {
        // Get the structureDelegataire
        restStructureDelegataireMockMvc.perform(get("/api/structure-delegataires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStructureDelegataire() throws Exception {
        // Initialize the database
        structureDelegataireRepository.saveAndFlush(structureDelegataire);

        int databaseSizeBeforeUpdate = structureDelegataireRepository.findAll().size();

        // Update the structureDelegataire
        StructureDelegataire updatedStructureDelegataire = structureDelegataireRepository.findById(structureDelegataire.getId()).get();
        // Disconnect from session so that the updates on updatedStructureDelegataire are not directly saved in db
        em.detach(updatedStructureDelegataire);
        updatedStructureDelegataire
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);

        restStructureDelegataireMockMvc.perform(put("/api/structure-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStructureDelegataire)))
            .andExpect(status().isOk());

        // Validate the StructureDelegataire in the database
        List<StructureDelegataire> structureDelegataireList = structureDelegataireRepository.findAll();
        assertThat(structureDelegataireList).hasSize(databaseSizeBeforeUpdate);
        StructureDelegataire testStructureDelegataire = structureDelegataireList.get(structureDelegataireList.size() - 1);
        assertThat(testStructureDelegataire.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testStructureDelegataire.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingStructureDelegataire() throws Exception {
        int databaseSizeBeforeUpdate = structureDelegataireRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStructureDelegataireMockMvc.perform(put("/api/structure-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(structureDelegataire)))
            .andExpect(status().isBadRequest());

        // Validate the StructureDelegataire in the database
        List<StructureDelegataire> structureDelegataireList = structureDelegataireRepository.findAll();
        assertThat(structureDelegataireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStructureDelegataire() throws Exception {
        // Initialize the database
        structureDelegataireRepository.saveAndFlush(structureDelegataire);

        int databaseSizeBeforeDelete = structureDelegataireRepository.findAll().size();

        // Delete the structureDelegataire
        restStructureDelegataireMockMvc.perform(delete("/api/structure-delegataires/{id}", structureDelegataire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StructureDelegataire> structureDelegataireList = structureDelegataireRepository.findAll();
        assertThat(structureDelegataireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
