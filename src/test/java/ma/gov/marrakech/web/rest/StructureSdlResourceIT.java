package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.MarrakechBackApp;
import ma.gov.marrakech.domain.StructureSdl;
import ma.gov.marrakech.repository.StructureSdlRepository;

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
 * Integration tests for the {@link StructureSdlResource} REST controller.
 */
@SpringBootTest(classes = MarrakechBackApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StructureSdlResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private StructureSdlRepository structureSdlRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStructureSdlMockMvc;

    private StructureSdl structureSdl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StructureSdl createEntity(EntityManager em) {
        StructureSdl structureSdl = new StructureSdl()
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION);
        return structureSdl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StructureSdl createUpdatedEntity(EntityManager em) {
        StructureSdl structureSdl = new StructureSdl()
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);
        return structureSdl;
    }

    @BeforeEach
    public void initTest() {
        structureSdl = createEntity(em);
    }

    @Test
    @Transactional
    public void createStructureSdl() throws Exception {
        int databaseSizeBeforeCreate = structureSdlRepository.findAll().size();
        // Create the StructureSdl
        restStructureSdlMockMvc.perform(post("/api/structure-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(structureSdl)))
            .andExpect(status().isCreated());

        // Validate the StructureSdl in the database
        List<StructureSdl> structureSdlList = structureSdlRepository.findAll();
        assertThat(structureSdlList).hasSize(databaseSizeBeforeCreate + 1);
        StructureSdl testStructureSdl = structureSdlList.get(structureSdlList.size() - 1);
        assertThat(testStructureSdl.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testStructureSdl.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createStructureSdlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = structureSdlRepository.findAll().size();

        // Create the StructureSdl with an existing ID
        structureSdl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStructureSdlMockMvc.perform(post("/api/structure-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(structureSdl)))
            .andExpect(status().isBadRequest());

        // Validate the StructureSdl in the database
        List<StructureSdl> structureSdlList = structureSdlRepository.findAll();
        assertThat(structureSdlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStructureSdls() throws Exception {
        // Initialize the database
        structureSdlRepository.saveAndFlush(structureSdl);

        // Get all the structureSdlList
        restStructureSdlMockMvc.perform(get("/api/structure-sdls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(structureSdl.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getStructureSdl() throws Exception {
        // Initialize the database
        structureSdlRepository.saveAndFlush(structureSdl);

        // Get the structureSdl
        restStructureSdlMockMvc.perform(get("/api/structure-sdls/{id}", structureSdl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(structureSdl.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingStructureSdl() throws Exception {
        // Get the structureSdl
        restStructureSdlMockMvc.perform(get("/api/structure-sdls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStructureSdl() throws Exception {
        // Initialize the database
        structureSdlRepository.saveAndFlush(structureSdl);

        int databaseSizeBeforeUpdate = structureSdlRepository.findAll().size();

        // Update the structureSdl
        StructureSdl updatedStructureSdl = structureSdlRepository.findById(structureSdl.getId()).get();
        // Disconnect from session so that the updates on updatedStructureSdl are not directly saved in db
        em.detach(updatedStructureSdl);
        updatedStructureSdl
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);

        restStructureSdlMockMvc.perform(put("/api/structure-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStructureSdl)))
            .andExpect(status().isOk());

        // Validate the StructureSdl in the database
        List<StructureSdl> structureSdlList = structureSdlRepository.findAll();
        assertThat(structureSdlList).hasSize(databaseSizeBeforeUpdate);
        StructureSdl testStructureSdl = structureSdlList.get(structureSdlList.size() - 1);
        assertThat(testStructureSdl.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testStructureSdl.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingStructureSdl() throws Exception {
        int databaseSizeBeforeUpdate = structureSdlRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStructureSdlMockMvc.perform(put("/api/structure-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(structureSdl)))
            .andExpect(status().isBadRequest());

        // Validate the StructureSdl in the database
        List<StructureSdl> structureSdlList = structureSdlRepository.findAll();
        assertThat(structureSdlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStructureSdl() throws Exception {
        // Initialize the database
        structureSdlRepository.saveAndFlush(structureSdl);

        int databaseSizeBeforeDelete = structureSdlRepository.findAll().size();

        // Delete the structureSdl
        restStructureSdlMockMvc.perform(delete("/api/structure-sdls/{id}", structureSdl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StructureSdl> structureSdlList = structureSdlRepository.findAll();
        assertThat(structureSdlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
