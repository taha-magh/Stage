package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.MarrakechBackApp;
import ma.gov.marrakech.domain.SuiviDelegataire;
import ma.gov.marrakech.repository.SuiviDelegataireRepository;

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
 * Integration tests for the {@link SuiviDelegataireResource} REST controller.
 */
@SpringBootTest(classes = MarrakechBackApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SuiviDelegataireResourceIT {

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
    private SuiviDelegataireRepository suiviDelegataireRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSuiviDelegataireMockMvc;

    private SuiviDelegataire suiviDelegataire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SuiviDelegataire createEntity(EntityManager em) {
        SuiviDelegataire suiviDelegataire = new SuiviDelegataire()
            .typeIndicateur(DEFAULT_TYPE_INDICATEUR)
            .nom(DEFAULT_NOM)
            .description(DEFAULT_DESCRIPTION)
            .dateValeur(DEFAULT_DATE_VALEUR)
            .valeurContractuel(DEFAULT_VALEUR_CONTRACTUEL)
            .valeurConstate(DEFAULT_VALEUR_CONSTATE);
        return suiviDelegataire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SuiviDelegataire createUpdatedEntity(EntityManager em) {
        SuiviDelegataire suiviDelegataire = new SuiviDelegataire()
            .typeIndicateur(UPDATED_TYPE_INDICATEUR)
            .nom(UPDATED_NOM)
            .description(UPDATED_DESCRIPTION)
            .dateValeur(UPDATED_DATE_VALEUR)
            .valeurContractuel(UPDATED_VALEUR_CONTRACTUEL)
            .valeurConstate(UPDATED_VALEUR_CONSTATE);
        return suiviDelegataire;
    }

    @BeforeEach
    public void initTest() {
        suiviDelegataire = createEntity(em);
    }

    @Test
    @Transactional
    public void createSuiviDelegataire() throws Exception {
        int databaseSizeBeforeCreate = suiviDelegataireRepository.findAll().size();
        // Create the SuiviDelegataire
        restSuiviDelegataireMockMvc.perform(post("/api/suivi-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(suiviDelegataire)))
            .andExpect(status().isCreated());

        // Validate the SuiviDelegataire in the database
        List<SuiviDelegataire> suiviDelegataireList = suiviDelegataireRepository.findAll();
        assertThat(suiviDelegataireList).hasSize(databaseSizeBeforeCreate + 1);
        SuiviDelegataire testSuiviDelegataire = suiviDelegataireList.get(suiviDelegataireList.size() - 1);
        assertThat(testSuiviDelegataire.getTypeIndicateur()).isEqualTo(DEFAULT_TYPE_INDICATEUR);
        assertThat(testSuiviDelegataire.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testSuiviDelegataire.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSuiviDelegataire.getDateValeur()).isEqualTo(DEFAULT_DATE_VALEUR);
        assertThat(testSuiviDelegataire.getValeurContractuel()).isEqualTo(DEFAULT_VALEUR_CONTRACTUEL);
        assertThat(testSuiviDelegataire.getValeurConstate()).isEqualTo(DEFAULT_VALEUR_CONSTATE);
    }

    @Test
    @Transactional
    public void createSuiviDelegataireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = suiviDelegataireRepository.findAll().size();

        // Create the SuiviDelegataire with an existing ID
        suiviDelegataire.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSuiviDelegataireMockMvc.perform(post("/api/suivi-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(suiviDelegataire)))
            .andExpect(status().isBadRequest());

        // Validate the SuiviDelegataire in the database
        List<SuiviDelegataire> suiviDelegataireList = suiviDelegataireRepository.findAll();
        assertThat(suiviDelegataireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSuiviDelegataires() throws Exception {
        // Initialize the database
        suiviDelegataireRepository.saveAndFlush(suiviDelegataire);

        // Get all the suiviDelegataireList
        restSuiviDelegataireMockMvc.perform(get("/api/suivi-delegataires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(suiviDelegataire.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeIndicateur").value(hasItem(DEFAULT_TYPE_INDICATEUR)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateValeur").value(hasItem(DEFAULT_DATE_VALEUR)))
            .andExpect(jsonPath("$.[*].valeurContractuel").value(hasItem(DEFAULT_VALEUR_CONTRACTUEL)))
            .andExpect(jsonPath("$.[*].valeurConstate").value(hasItem(DEFAULT_VALEUR_CONSTATE)));
    }
    
    @Test
    @Transactional
    public void getSuiviDelegataire() throws Exception {
        // Initialize the database
        suiviDelegataireRepository.saveAndFlush(suiviDelegataire);

        // Get the suiviDelegataire
        restSuiviDelegataireMockMvc.perform(get("/api/suivi-delegataires/{id}", suiviDelegataire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(suiviDelegataire.getId().intValue()))
            .andExpect(jsonPath("$.typeIndicateur").value(DEFAULT_TYPE_INDICATEUR))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.dateValeur").value(DEFAULT_DATE_VALEUR))
            .andExpect(jsonPath("$.valeurContractuel").value(DEFAULT_VALEUR_CONTRACTUEL))
            .andExpect(jsonPath("$.valeurConstate").value(DEFAULT_VALEUR_CONSTATE));
    }
    @Test
    @Transactional
    public void getNonExistingSuiviDelegataire() throws Exception {
        // Get the suiviDelegataire
        restSuiviDelegataireMockMvc.perform(get("/api/suivi-delegataires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSuiviDelegataire() throws Exception {
        // Initialize the database
        suiviDelegataireRepository.saveAndFlush(suiviDelegataire);

        int databaseSizeBeforeUpdate = suiviDelegataireRepository.findAll().size();

        // Update the suiviDelegataire
        SuiviDelegataire updatedSuiviDelegataire = suiviDelegataireRepository.findById(suiviDelegataire.getId()).get();
        // Disconnect from session so that the updates on updatedSuiviDelegataire are not directly saved in db
        em.detach(updatedSuiviDelegataire);
        updatedSuiviDelegataire
            .typeIndicateur(UPDATED_TYPE_INDICATEUR)
            .nom(UPDATED_NOM)
            .description(UPDATED_DESCRIPTION)
            .dateValeur(UPDATED_DATE_VALEUR)
            .valeurContractuel(UPDATED_VALEUR_CONTRACTUEL)
            .valeurConstate(UPDATED_VALEUR_CONSTATE);

        restSuiviDelegataireMockMvc.perform(put("/api/suivi-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSuiviDelegataire)))
            .andExpect(status().isOk());

        // Validate the SuiviDelegataire in the database
        List<SuiviDelegataire> suiviDelegataireList = suiviDelegataireRepository.findAll();
        assertThat(suiviDelegataireList).hasSize(databaseSizeBeforeUpdate);
        SuiviDelegataire testSuiviDelegataire = suiviDelegataireList.get(suiviDelegataireList.size() - 1);
        assertThat(testSuiviDelegataire.getTypeIndicateur()).isEqualTo(UPDATED_TYPE_INDICATEUR);
        assertThat(testSuiviDelegataire.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testSuiviDelegataire.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSuiviDelegataire.getDateValeur()).isEqualTo(UPDATED_DATE_VALEUR);
        assertThat(testSuiviDelegataire.getValeurContractuel()).isEqualTo(UPDATED_VALEUR_CONTRACTUEL);
        assertThat(testSuiviDelegataire.getValeurConstate()).isEqualTo(UPDATED_VALEUR_CONSTATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSuiviDelegataire() throws Exception {
        int databaseSizeBeforeUpdate = suiviDelegataireRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSuiviDelegataireMockMvc.perform(put("/api/suivi-delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(suiviDelegataire)))
            .andExpect(status().isBadRequest());

        // Validate the SuiviDelegataire in the database
        List<SuiviDelegataire> suiviDelegataireList = suiviDelegataireRepository.findAll();
        assertThat(suiviDelegataireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSuiviDelegataire() throws Exception {
        // Initialize the database
        suiviDelegataireRepository.saveAndFlush(suiviDelegataire);

        int databaseSizeBeforeDelete = suiviDelegataireRepository.findAll().size();

        // Delete the suiviDelegataire
        restSuiviDelegataireMockMvc.perform(delete("/api/suivi-delegataires/{id}", suiviDelegataire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SuiviDelegataire> suiviDelegataireList = suiviDelegataireRepository.findAll();
        assertThat(suiviDelegataireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
