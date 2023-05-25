package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.MarrakechBackApp;
import ma.gov.marrakech.domain.TypeIndicSdl;
import ma.gov.marrakech.repository.TypeIndicSdlRepository;

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
 * Integration tests for the {@link TypeIndicSdlResource} REST controller.
 */
@SpringBootTest(classes = MarrakechBackApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeIndicSdlResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private TypeIndicSdlRepository typeIndicSdlRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeIndicSdlMockMvc;

    private TypeIndicSdl typeIndicSdl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeIndicSdl createEntity(EntityManager em) {
        TypeIndicSdl typeIndicSdl = new TypeIndicSdl()
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION);
        return typeIndicSdl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeIndicSdl createUpdatedEntity(EntityManager em) {
        TypeIndicSdl typeIndicSdl = new TypeIndicSdl()
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);
        return typeIndicSdl;
    }

    @BeforeEach
    public void initTest() {
        typeIndicSdl = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeIndicSdl() throws Exception {
        int databaseSizeBeforeCreate = typeIndicSdlRepository.findAll().size();
        // Create the TypeIndicSdl
        restTypeIndicSdlMockMvc.perform(post("/api/type-indic-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeIndicSdl)))
            .andExpect(status().isCreated());

        // Validate the TypeIndicSdl in the database
        List<TypeIndicSdl> typeIndicSdlList = typeIndicSdlRepository.findAll();
        assertThat(typeIndicSdlList).hasSize(databaseSizeBeforeCreate + 1);
        TypeIndicSdl testTypeIndicSdl = typeIndicSdlList.get(typeIndicSdlList.size() - 1);
        assertThat(testTypeIndicSdl.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testTypeIndicSdl.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createTypeIndicSdlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeIndicSdlRepository.findAll().size();

        // Create the TypeIndicSdl with an existing ID
        typeIndicSdl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeIndicSdlMockMvc.perform(post("/api/type-indic-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeIndicSdl)))
            .andExpect(status().isBadRequest());

        // Validate the TypeIndicSdl in the database
        List<TypeIndicSdl> typeIndicSdlList = typeIndicSdlRepository.findAll();
        assertThat(typeIndicSdlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeIndicSdls() throws Exception {
        // Initialize the database
        typeIndicSdlRepository.saveAndFlush(typeIndicSdl);

        // Get all the typeIndicSdlList
        restTypeIndicSdlMockMvc.perform(get("/api/type-indic-sdls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeIndicSdl.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getTypeIndicSdl() throws Exception {
        // Initialize the database
        typeIndicSdlRepository.saveAndFlush(typeIndicSdl);

        // Get the typeIndicSdl
        restTypeIndicSdlMockMvc.perform(get("/api/type-indic-sdls/{id}", typeIndicSdl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeIndicSdl.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingTypeIndicSdl() throws Exception {
        // Get the typeIndicSdl
        restTypeIndicSdlMockMvc.perform(get("/api/type-indic-sdls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeIndicSdl() throws Exception {
        // Initialize the database
        typeIndicSdlRepository.saveAndFlush(typeIndicSdl);

        int databaseSizeBeforeUpdate = typeIndicSdlRepository.findAll().size();

        // Update the typeIndicSdl
        TypeIndicSdl updatedTypeIndicSdl = typeIndicSdlRepository.findById(typeIndicSdl.getId()).get();
        // Disconnect from session so that the updates on updatedTypeIndicSdl are not directly saved in db
        em.detach(updatedTypeIndicSdl);
        updatedTypeIndicSdl
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);

        restTypeIndicSdlMockMvc.perform(put("/api/type-indic-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeIndicSdl)))
            .andExpect(status().isOk());

        // Validate the TypeIndicSdl in the database
        List<TypeIndicSdl> typeIndicSdlList = typeIndicSdlRepository.findAll();
        assertThat(typeIndicSdlList).hasSize(databaseSizeBeforeUpdate);
        TypeIndicSdl testTypeIndicSdl = typeIndicSdlList.get(typeIndicSdlList.size() - 1);
        assertThat(testTypeIndicSdl.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testTypeIndicSdl.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeIndicSdl() throws Exception {
        int databaseSizeBeforeUpdate = typeIndicSdlRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeIndicSdlMockMvc.perform(put("/api/type-indic-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeIndicSdl)))
            .andExpect(status().isBadRequest());

        // Validate the TypeIndicSdl in the database
        List<TypeIndicSdl> typeIndicSdlList = typeIndicSdlRepository.findAll();
        assertThat(typeIndicSdlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeIndicSdl() throws Exception {
        // Initialize the database
        typeIndicSdlRepository.saveAndFlush(typeIndicSdl);

        int databaseSizeBeforeDelete = typeIndicSdlRepository.findAll().size();

        // Delete the typeIndicSdl
        restTypeIndicSdlMockMvc.perform(delete("/api/type-indic-sdls/{id}", typeIndicSdl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeIndicSdl> typeIndicSdlList = typeIndicSdlRepository.findAll();
        assertThat(typeIndicSdlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
