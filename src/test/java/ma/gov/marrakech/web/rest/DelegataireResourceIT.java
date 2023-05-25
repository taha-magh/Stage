package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.MarrakechBackApp;
import ma.gov.marrakech.domain.Delegataire;
import ma.gov.marrakech.repository.DelegataireRepository;

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
 * Integration tests for the {@link DelegataireResource} REST controller.
 */
@SpringBootTest(classes = MarrakechBackApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DelegataireResourceIT {

    private static final String DEFAULT_RAISON_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_RAISON_COM = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_COM = "BBBBBBBBBB";

    private static final String DEFAULT_ID_FISCALE = "AAAAAAAAAA";
    private static final String UPDATED_ID_FISCALE = "BBBBBBBBBB";

    private static final String DEFAULT_ICE = "AAAAAAAAAA";
    private static final String UPDATED_ICE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_CNSS = "AAAAAAAAAA";
    private static final String UPDATED_NUM_CNSS = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_GERANT = "AAAAAAAAAA";
    private static final String UPDATED_GERANT = "BBBBBBBBBB";

    private static final String DEFAULT_STRUCTURE = "AAAAAAAAAA";
    private static final String UPDATED_STRUCTURE = "BBBBBBBBBB";

    private static final String DEFAULT_CAPITAL = "AAAAAAAAAA";
    private static final String UPDATED_CAPITAL = "BBBBBBBBBB";

    @Autowired
    private DelegataireRepository delegataireRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDelegataireMockMvc;

    private Delegataire delegataire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Delegataire createEntity(EntityManager em) {
        Delegataire delegataire = new Delegataire()
            .raisonSocial(DEFAULT_RAISON_SOCIAL)
            .raisonCom(DEFAULT_RAISON_COM)
            .idFiscale(DEFAULT_ID_FISCALE)
            .ice(DEFAULT_ICE)
            .numCnss(DEFAULT_NUM_CNSS)
            .adresse(DEFAULT_ADRESSE)
            .tel(DEFAULT_TEL)
            .fax(DEFAULT_FAX)
            .email(DEFAULT_EMAIL)
            .gerant(DEFAULT_GERANT)
            .structure(DEFAULT_STRUCTURE)
            .capital(DEFAULT_CAPITAL);
        return delegataire;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Delegataire createUpdatedEntity(EntityManager em) {
        Delegataire delegataire = new Delegataire()
            .raisonSocial(UPDATED_RAISON_SOCIAL)
            .raisonCom(UPDATED_RAISON_COM)
            .idFiscale(UPDATED_ID_FISCALE)
            .ice(UPDATED_ICE)
            .numCnss(UPDATED_NUM_CNSS)
            .adresse(UPDATED_ADRESSE)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .gerant(UPDATED_GERANT)
            .structure(UPDATED_STRUCTURE)
            .capital(UPDATED_CAPITAL);
        return delegataire;
    }

    @BeforeEach
    public void initTest() {
        delegataire = createEntity(em);
    }

    @Test
    @Transactional
    public void createDelegataire() throws Exception {
        int databaseSizeBeforeCreate = delegataireRepository.findAll().size();
        // Create the Delegataire
        restDelegataireMockMvc.perform(post("/api/delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(delegataire)))
            .andExpect(status().isCreated());

        // Validate the Delegataire in the database
        List<Delegataire> delegataireList = delegataireRepository.findAll();
        assertThat(delegataireList).hasSize(databaseSizeBeforeCreate + 1);
        Delegataire testDelegataire = delegataireList.get(delegataireList.size() - 1);
        assertThat(testDelegataire.getRaisonSocial()).isEqualTo(DEFAULT_RAISON_SOCIAL);
        assertThat(testDelegataire.getRaisonCom()).isEqualTo(DEFAULT_RAISON_COM);
        assertThat(testDelegataire.getIdFiscale()).isEqualTo(DEFAULT_ID_FISCALE);
        assertThat(testDelegataire.getIce()).isEqualTo(DEFAULT_ICE);
        assertThat(testDelegataire.getNumCnss()).isEqualTo(DEFAULT_NUM_CNSS);
        assertThat(testDelegataire.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testDelegataire.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testDelegataire.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testDelegataire.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDelegataire.getGerant()).isEqualTo(DEFAULT_GERANT);
        assertThat(testDelegataire.getStructure()).isEqualTo(DEFAULT_STRUCTURE);
        assertThat(testDelegataire.getCapital()).isEqualTo(DEFAULT_CAPITAL);
    }

    @Test
    @Transactional
    public void createDelegataireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = delegataireRepository.findAll().size();

        // Create the Delegataire with an existing ID
        delegataire.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDelegataireMockMvc.perform(post("/api/delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(delegataire)))
            .andExpect(status().isBadRequest());

        // Validate the Delegataire in the database
        List<Delegataire> delegataireList = delegataireRepository.findAll();
        assertThat(delegataireList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDelegataires() throws Exception {
        // Initialize the database
        delegataireRepository.saveAndFlush(delegataire);

        // Get all the delegataireList
        restDelegataireMockMvc.perform(get("/api/delegataires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(delegataire.getId().intValue())))
            .andExpect(jsonPath("$.[*].raisonSocial").value(hasItem(DEFAULT_RAISON_SOCIAL)))
            .andExpect(jsonPath("$.[*].raisonCom").value(hasItem(DEFAULT_RAISON_COM)))
            .andExpect(jsonPath("$.[*].idFiscale").value(hasItem(DEFAULT_ID_FISCALE)))
            .andExpect(jsonPath("$.[*].ice").value(hasItem(DEFAULT_ICE)))
            .andExpect(jsonPath("$.[*].numCnss").value(hasItem(DEFAULT_NUM_CNSS)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].gerant").value(hasItem(DEFAULT_GERANT)))
            .andExpect(jsonPath("$.[*].structure").value(hasItem(DEFAULT_STRUCTURE)))
            .andExpect(jsonPath("$.[*].capital").value(hasItem(DEFAULT_CAPITAL)));
    }
    
    @Test
    @Transactional
    public void getDelegataire() throws Exception {
        // Initialize the database
        delegataireRepository.saveAndFlush(delegataire);

        // Get the delegataire
        restDelegataireMockMvc.perform(get("/api/delegataires/{id}", delegataire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(delegataire.getId().intValue()))
            .andExpect(jsonPath("$.raisonSocial").value(DEFAULT_RAISON_SOCIAL))
            .andExpect(jsonPath("$.raisonCom").value(DEFAULT_RAISON_COM))
            .andExpect(jsonPath("$.idFiscale").value(DEFAULT_ID_FISCALE))
            .andExpect(jsonPath("$.ice").value(DEFAULT_ICE))
            .andExpect(jsonPath("$.numCnss").value(DEFAULT_NUM_CNSS))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.gerant").value(DEFAULT_GERANT))
            .andExpect(jsonPath("$.structure").value(DEFAULT_STRUCTURE))
            .andExpect(jsonPath("$.capital").value(DEFAULT_CAPITAL));
    }
    @Test
    @Transactional
    public void getNonExistingDelegataire() throws Exception {
        // Get the delegataire
        restDelegataireMockMvc.perform(get("/api/delegataires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDelegataire() throws Exception {
        // Initialize the database
        delegataireRepository.saveAndFlush(delegataire);

        int databaseSizeBeforeUpdate = delegataireRepository.findAll().size();

        // Update the delegataire
        Delegataire updatedDelegataire = delegataireRepository.findById(delegataire.getId()).get();
        // Disconnect from session so that the updates on updatedDelegataire are not directly saved in db
        em.detach(updatedDelegataire);
        updatedDelegataire
            .raisonSocial(UPDATED_RAISON_SOCIAL)
            .raisonCom(UPDATED_RAISON_COM)
            .idFiscale(UPDATED_ID_FISCALE)
            .ice(UPDATED_ICE)
            .numCnss(UPDATED_NUM_CNSS)
            .adresse(UPDATED_ADRESSE)
            .tel(UPDATED_TEL)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .gerant(UPDATED_GERANT)
            .structure(UPDATED_STRUCTURE)
            .capital(UPDATED_CAPITAL);

        restDelegataireMockMvc.perform(put("/api/delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDelegataire)))
            .andExpect(status().isOk());

        // Validate the Delegataire in the database
        List<Delegataire> delegataireList = delegataireRepository.findAll();
        assertThat(delegataireList).hasSize(databaseSizeBeforeUpdate);
        Delegataire testDelegataire = delegataireList.get(delegataireList.size() - 1);
        assertThat(testDelegataire.getRaisonSocial()).isEqualTo(UPDATED_RAISON_SOCIAL);
        assertThat(testDelegataire.getRaisonCom()).isEqualTo(UPDATED_RAISON_COM);
        assertThat(testDelegataire.getIdFiscale()).isEqualTo(UPDATED_ID_FISCALE);
        assertThat(testDelegataire.getIce()).isEqualTo(UPDATED_ICE);
        assertThat(testDelegataire.getNumCnss()).isEqualTo(UPDATED_NUM_CNSS);
        assertThat(testDelegataire.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testDelegataire.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testDelegataire.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testDelegataire.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDelegataire.getGerant()).isEqualTo(UPDATED_GERANT);
        assertThat(testDelegataire.getStructure()).isEqualTo(UPDATED_STRUCTURE);
        assertThat(testDelegataire.getCapital()).isEqualTo(UPDATED_CAPITAL);
    }

    @Test
    @Transactional
    public void updateNonExistingDelegataire() throws Exception {
        int databaseSizeBeforeUpdate = delegataireRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDelegataireMockMvc.perform(put("/api/delegataires")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(delegataire)))
            .andExpect(status().isBadRequest());

        // Validate the Delegataire in the database
        List<Delegataire> delegataireList = delegataireRepository.findAll();
        assertThat(delegataireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDelegataire() throws Exception {
        // Initialize the database
        delegataireRepository.saveAndFlush(delegataire);

        int databaseSizeBeforeDelete = delegataireRepository.findAll().size();

        // Delete the delegataire
        restDelegataireMockMvc.perform(delete("/api/delegataires/{id}", delegataire.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Delegataire> delegataireList = delegataireRepository.findAll();
        assertThat(delegataireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
