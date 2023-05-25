package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.MarrakechBackApp;
import ma.gov.marrakech.domain.TypeIndicDelegataire;
import ma.gov.marrakech.repository.TypeIndicDelegataireRepository;

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
 * Integration tests for the {@link TypeIndicDelegataireResource} REST controller.
 */
@SpringBootTest(classes = MarrakechBackApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeIndicDelegataireResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private TypeIndicDelegataireRepository typeIndicDelegataireRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeIndicDelegataireMockMvc;

    private TypeIndicDelegataire typeIndicDelegataire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeIndicDelegataire createEntity(EntityManager em) {
        TypeIndicDelegataire typeIndicDelegataire = new TypeIndicDelegataire()
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION);
        return typeIndicDelegataire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeIndicDelegataire createUpdatedEntity(EntityManager em) {
        TypeIndicDelegataire typeIndicDelegataire = new TypeIndicDelegataire()
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);
        return typeIndicDelegataire;
    }

    @BeforeEach
    public void initTest() {
        typeIndicDelegataire = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeIndicDelegataire() throws Exception {
        int databaseSizeBeforeCreate = typeIndicDelegataireRepository.findAll().size();
        // Create the TypeIndicDelegataire
        restTypeIndicDelegataireMockMvc.perform(post("/api/type-indic-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeIndicDelegataire)))
            .andExpect(status().isCreated());

        // Validate the TypeIndicDelegataire in the database
        List<TypeIndicDelegataire> typeIndicDelegataireList = typeIndicDelegataireRepository.findAll();
        assertThat(typeIndicDelegataireList).hasSize(databaseSizeBeforeCreate + 1);
        TypeIndicDelegataire testTypeIndicDelegataire = typeIndicDelegataireList.get(typeIndicDelegataireList.size() - 1);
        assertThat(testTypeIndicDelegataire.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testTypeIndicDelegataire.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createTypeIndicDelegataireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeIndicDelegataireRepository.findAll().size();

        // Create the TypeIndicDelegataire with an existing ID
        typeIndicDelegataire.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeIndicDelegataireMockMvc.perform(post("/api/type-indic-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeIndicDelegataire)))
            .andExpect(status().isBadRequest());

        // Validate the TypeIndicDelegataire in the database
        List<TypeIndicDelegataire> typeIndicDelegataireList = typeIndicDelegataireRepository.findAll();
        assertThat(typeIndicDelegataireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeIndicDelegataires() throws Exception {
        // Initialize the database
        typeIndicDelegataireRepository.saveAndFlush(typeIndicDelegataire);

        // Get all the typeIndicDelegataireList
        restTypeIndicDelegataireMockMvc.perform(get("/api/type-indic-delegataires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeIndicDelegataire.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getTypeIndicDelegataire() throws Exception {
        // Initialize the database
        typeIndicDelegataireRepository.saveAndFlush(typeIndicDelegataire);

        // Get the typeIndicDelegataire
        restTypeIndicDelegataireMockMvc.perform(get("/api/type-indic-delegataires/{id}", typeIndicDelegataire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeIndicDelegataire.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingTypeIndicDelegataire() throws Exception {
        // Get the typeIndicDelegataire
        restTypeIndicDelegataireMockMvc.perform(get("/api/type-indic-delegataires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeIndicDelegataire() throws Exception {
        // Initialize the database
        typeIndicDelegataireRepository.saveAndFlush(typeIndicDelegataire);

        int databaseSizeBeforeUpdate = typeIndicDelegataireRepository.findAll().size();

        // Update the typeIndicDelegataire
        TypeIndicDelegataire updatedTypeIndicDelegataire = typeIndicDelegataireRepository.findById(typeIndicDelegataire.getId()).get();
        // Disconnect from session so that the updates on updatedTypeIndicDelegataire are not directly saved in db
        em.detach(updatedTypeIndicDelegataire);
        updatedTypeIndicDelegataire
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);

        restTypeIndicDelegataireMockMvc.perform(put("/api/type-indic-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeIndicDelegataire)))
            .andExpect(status().isOk());

        // Validate the TypeIndicDelegataire in the database
        List<TypeIndicDelegataire> typeIndicDelegataireList = typeIndicDelegataireRepository.findAll();
        assertThat(typeIndicDelegataireList).hasSize(databaseSizeBeforeUpdate);
        TypeIndicDelegataire testTypeIndicDelegataire = typeIndicDelegataireList.get(typeIndicDelegataireList.size() - 1);
        assertThat(testTypeIndicDelegataire.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testTypeIndicDelegataire.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeIndicDelegataire() throws Exception {
        int databaseSizeBeforeUpdate = typeIndicDelegataireRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeIndicDelegataireMockMvc.perform(put("/api/type-indic-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeIndicDelegataire)))
            .andExpect(status().isBadRequest());

        // Validate the TypeIndicDelegataire in the database
        List<TypeIndicDelegataire> typeIndicDelegataireList = typeIndicDelegataireRepository.findAll();
        assertThat(typeIndicDelegataireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeIndicDelegataire() throws Exception {
        // Initialize the database
        typeIndicDelegataireRepository.saveAndFlush(typeIndicDelegataire);

        int databaseSizeBeforeDelete = typeIndicDelegataireRepository.findAll().size();

        // Delete the typeIndicDelegataire
        restTypeIndicDelegataireMockMvc.perform(delete("/api/type-indic-delegataires/{id}", typeIndicDelegataire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeIndicDelegataire> typeIndicDelegataireList = typeIndicDelegataireRepository.findAll();
        assertThat(typeIndicDelegataireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
