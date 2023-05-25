package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.MarrakechBackApp;
import ma.gov.marrakech.domain.SuiviSdl;
import ma.gov.marrakech.repository.SuiviSdlRepository;

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
 * Integration tests for the {@link SuiviSdlResource} REST controller.
 */
@SpringBootTest(classes = MarrakechBackApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SuiviSdlResourceIT {

    private static final String DEFAULT_TYPE_INDICATEUR = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_INDICATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_VALEUR = "AAAAAAAAAA";
    private static final String UPDATED_DATE_VALEUR = "BBBBBBBBBB";

    private static final String DEFAULT_VALEUR_CONTRACTUEL = "AAAAAAAAAA";
    private static final String UPDATED_VALEUR_CONTRACTUEL = "BBBBBBBBBB";

    private static final String DEFAULT_VALEUR_CONSTATE = "AAAAAAAAAA";
    private static final String UPDATED_VALEUR_CONSTATE = "BBBBBBBBBB";

    @Autowired
    private SuiviSdlRepository suiviSdlRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSuiviSdlMockMvc;

    private SuiviSdl suiviSdl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SuiviSdl createEntity(EntityManager em) {
        SuiviSdl suiviSdl = new SuiviSdl()
            .typeIndicateur(DEFAULT_TYPE_INDICATEUR)
            .nom(DEFAULT_NOM)
            .description(DEFAULT_DESCRIPTION)
            .dateValeur(DEFAULT_DATE_VALEUR)
            .valeurContractuel(DEFAULT_VALEUR_CONTRACTUEL)
            .valeurConstate(DEFAULT_VALEUR_CONSTATE);
        return suiviSdl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SuiviSdl createUpdatedEntity(EntityManager em) {
        SuiviSdl suiviSdl = new SuiviSdl()
            .typeIndicateur(UPDATED_TYPE_INDICATEUR)
            .nom(UPDATED_NOM)
            .description(UPDATED_DESCRIPTION)
            .dateValeur(UPDATED_DATE_VALEUR)
            .valeurContractuel(UPDATED_VALEUR_CONTRACTUEL)
            .valeurConstate(UPDATED_VALEUR_CONSTATE);
        return suiviSdl;
    }

    @BeforeEach
    public void initTest() {
        suiviSdl = createEntity(em);
    }

    @Test
    @Transactional
    public void createSuiviSdl() throws Exception {
        int databaseSizeBeforeCreate = suiviSdlRepository.findAll().size();
        // Create the SuiviSdl
        restSuiviSdlMockMvc.perform(post("/api/suivi-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(suiviSdl)))
            .andExpect(status().isCreated());

        // Validate the SuiviSdl in the database
        List<SuiviSdl> suiviSdlList = suiviSdlRepository.findAll();
        assertThat(suiviSdlList).hasSize(databaseSizeBeforeCreate + 1);
        SuiviSdl testSuiviSdl = suiviSdlList.get(suiviSdlList.size() - 1);
        assertThat(testSuiviSdl.getTypeIndicateur()).isEqualTo(DEFAULT_TYPE_INDICATEUR);
        assertThat(testSuiviSdl.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testSuiviSdl.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSuiviSdl.getDateValeur()).isEqualTo(DEFAULT_DATE_VALEUR);
        assertThat(testSuiviSdl.getValeurContractuel()).isEqualTo(DEFAULT_VALEUR_CONTRACTUEL);
        assertThat(testSuiviSdl.getValeurConstate()).isEqualTo(DEFAULT_VALEUR_CONSTATE);
    }

    @Test
    @Transactional
    public void createSuiviSdlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = suiviSdlRepository.findAll().size();

        // Create the SuiviSdl with an existing ID
        suiviSdl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSuiviSdlMockMvc.perform(post("/api/suivi-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(suiviSdl)))
            .andExpect(status().isBadRequest());

        // Validate the SuiviSdl in the database
        List<SuiviSdl> suiviSdlList = suiviSdlRepository.findAll();
        assertThat(suiviSdlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSuiviSdls() throws Exception {
        // Initialize the database
        suiviSdlRepository.saveAndFlush(suiviSdl);

        // Get all the suiviSdlList
        restSuiviSdlMockMvc.perform(get("/api/suivi-sdls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(suiviSdl.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeIndicateur").value(hasItem(DEFAULT_TYPE_INDICATEUR)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateValeur").value(hasItem(DEFAULT_DATE_VALEUR)))
            .andExpect(jsonPath("$.[*].valeurContractuel").value(hasItem(DEFAULT_VALEUR_CONTRACTUEL)))
            .andExpect(jsonPath("$.[*].valeurConstate").value(hasItem(DEFAULT_VALEUR_CONSTATE)));
    }
    
    @Test
    @Transactional
    public void getSuiviSdl() throws Exception {
        // Initialize the database
        suiviSdlRepository.saveAndFlush(suiviSdl);

        // Get the suiviSdl
        restSuiviSdlMockMvc.perform(get("/api/suivi-sdls/{id}", suiviSdl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(suiviSdl.getId().intValue()))
            .andExpect(jsonPath("$.typeIndicateur").value(DEFAULT_TYPE_INDICATEUR))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.dateValeur").value(DEFAULT_DATE_VALEUR))
            .andExpect(jsonPath("$.valeurContractuel").value(DEFAULT_VALEUR_CONTRACTUEL))
            .andExpect(jsonPath("$.valeurConstate").value(DEFAULT_VALEUR_CONSTATE));
    }
    @Test
    @Transactional
    public void getNonExistingSuiviSdl() throws Exception {
        // Get the suiviSdl
        restSuiviSdlMockMvc.perform(get("/api/suivi-sdls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSuiviSdl() throws Exception {
        // Initialize the database
        suiviSdlRepository.saveAndFlush(suiviSdl);

        int databaseSizeBeforeUpdate = suiviSdlRepository.findAll().size();

        // Update the suiviSdl
        SuiviSdl updatedSuiviSdl = suiviSdlRepository.findById(suiviSdl.getId()).get();
        // Disconnect from session so that the updates on updatedSuiviSdl are not directly saved in db
        em.detach(updatedSuiviSdl);
        updatedSuiviSdl
            .typeIndicateur(UPDATED_TYPE_INDICATEUR)
            .nom(UPDATED_NOM)
            .description(UPDATED_DESCRIPTION)
            .dateValeur(UPDATED_DATE_VALEUR)
            .valeurContractuel(UPDATED_VALEUR_CONTRACTUEL)
            .valeurConstate(UPDATED_VALEUR_CONSTATE);

        restSuiviSdlMockMvc.perform(put("/api/suivi-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSuiviSdl)))
            .andExpect(status().isOk());

        // Validate the SuiviSdl in the database
        List<SuiviSdl> suiviSdlList = suiviSdlRepository.findAll();
        assertThat(suiviSdlList).hasSize(databaseSizeBeforeUpdate);
        SuiviSdl testSuiviSdl = suiviSdlList.get(suiviSdlList.size() - 1);
        assertThat(testSuiviSdl.getTypeIndicateur()).isEqualTo(UPDATED_TYPE_INDICATEUR);
        assertThat(testSuiviSdl.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testSuiviSdl.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSuiviSdl.getDateValeur()).isEqualTo(UPDATED_DATE_VALEUR);
        assertThat(testSuiviSdl.getValeurContractuel()).isEqualTo(UPDATED_VALEUR_CONTRACTUEL);
        assertThat(testSuiviSdl.getValeurConstate()).isEqualTo(UPDATED_VALEUR_CONSTATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSuiviSdl() throws Exception {
        int databaseSizeBeforeUpdate = suiviSdlRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSuiviSdlMockMvc.perform(put("/api/suivi-sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(suiviSdl)))
            .andExpect(status().isBadRequest());

        // Validate the SuiviSdl in the database
        List<SuiviSdl> suiviSdlList = suiviSdlRepository.findAll();
        assertThat(suiviSdlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSuiviSdl() throws Exception {
        // Initialize the database
        suiviSdlRepository.saveAndFlush(suiviSdl);

        int databaseSizeBeforeDelete = suiviSdlRepository.findAll().size();

        // Delete the suiviSdl
        restSuiviSdlMockMvc.perform(delete("/api/suivi-sdls/{id}", suiviSdl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SuiviSdl> suiviSdlList = suiviSdlRepository.findAll();
        assertThat(suiviSdlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
