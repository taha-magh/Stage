package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.MarrakechBackApp;
import ma.gov.marrakech.domain.Sdl;
import ma.gov.marrakech.repository.SdlRepository;

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
 * Integration tests for the {@link SdlResource} REST controller.
 */
@SpringBootTest(classes = MarrakechBackApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SdlResourceIT {

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
    private SdlRepository sdlRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSdlMockMvc;

    private Sdl sdl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sdl createEntity(EntityManager em) {
        Sdl sdl = new Sdl()
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
        return sdl;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sdl createUpdatedEntity(EntityManager em) {
        Sdl sdl = new Sdl()
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
        return sdl;
    }

    @BeforeEach
    public void initTest() {
        sdl = createEntity(em);
    }

    @Test
    @Transactional
    public void createSdl() throws Exception {
        int databaseSizeBeforeCreate = sdlRepository.findAll().size();
        // Create the Sdl
        restSdlMockMvc.perform(post("/api/sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sdl)))
            .andExpect(status().isCreated());

        // Validate the Sdl in the database
        List<Sdl> sdlList = sdlRepository.findAll();
        assertThat(sdlList).hasSize(databaseSizeBeforeCreate + 1);
        Sdl testSdl = sdlList.get(sdlList.size() - 1);
        assertThat(testSdl.getRaisonSocial()).isEqualTo(DEFAULT_RAISON_SOCIAL);
        assertThat(testSdl.getRaisonCom()).isEqualTo(DEFAULT_RAISON_COM);
        assertThat(testSdl.getIdFiscale()).isEqualTo(DEFAULT_ID_FISCALE);
        assertThat(testSdl.getIce()).isEqualTo(DEFAULT_ICE);
        assertThat(testSdl.getNumCnss()).isEqualTo(DEFAULT_NUM_CNSS);
        assertThat(testSdl.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testSdl.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testSdl.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testSdl.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSdl.getGerant()).isEqualTo(DEFAULT_GERANT);
        assertThat(testSdl.getStructure()).isEqualTo(DEFAULT_STRUCTURE);
        assertThat(testSdl.getCapital()).isEqualTo(DEFAULT_CAPITAL);
    }

    @Test
    @Transactional
    public void createSdlWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sdlRepository.findAll().size();

        // Create the Sdl with an existing ID
        sdl.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSdlMockMvc.perform(post("/api/sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sdl)))
            .andExpect(status().isBadRequest());

        // Validate the Sdl in the database
        List<Sdl> sdlList = sdlRepository.findAll();
        assertThat(sdlList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSdls() throws Exception {
        // Initialize the database
        sdlRepository.saveAndFlush(sdl);

        // Get all the sdlList
        restSdlMockMvc.perform(get("/api/sdls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sdl.getId().intValue())))
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
    public void getSdl() throws Exception {
        // Initialize the database
        sdlRepository.saveAndFlush(sdl);

        // Get the sdl
        restSdlMockMvc.perform(get("/api/sdls/{id}", sdl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sdl.getId().intValue()))
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
    public void getNonExistingSdl() throws Exception {
        // Get the sdl
        restSdlMockMvc.perform(get("/api/sdls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSdl() throws Exception {
        // Initialize the database
        sdlRepository.saveAndFlush(sdl);

        int databaseSizeBeforeUpdate = sdlRepository.findAll().size();

        // Update the sdl
        Sdl updatedSdl = sdlRepository.findById(sdl.getId()).get();
        // Disconnect from session so that the updates on updatedSdl are not directly saved in db
        em.detach(updatedSdl);
        updatedSdl
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

        restSdlMockMvc.perform(put("/api/sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSdl)))
            .andExpect(status().isOk());

        // Validate the Sdl in the database
        List<Sdl> sdlList = sdlRepository.findAll();
        assertThat(sdlList).hasSize(databaseSizeBeforeUpdate);
        Sdl testSdl = sdlList.get(sdlList.size() - 1);
        assertThat(testSdl.getRaisonSocial()).isEqualTo(UPDATED_RAISON_SOCIAL);
        assertThat(testSdl.getRaisonCom()).isEqualTo(UPDATED_RAISON_COM);
        assertThat(testSdl.getIdFiscale()).isEqualTo(UPDATED_ID_FISCALE);
        assertThat(testSdl.getIce()).isEqualTo(UPDATED_ICE);
        assertThat(testSdl.getNumCnss()).isEqualTo(UPDATED_NUM_CNSS);
        assertThat(testSdl.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testSdl.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testSdl.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testSdl.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSdl.getGerant()).isEqualTo(UPDATED_GERANT);
        assertThat(testSdl.getStructure()).isEqualTo(UPDATED_STRUCTURE);
        assertThat(testSdl.getCapital()).isEqualTo(UPDATED_CAPITAL);
    }

    @Test
    @Transactional
    public void updateNonExistingSdl() throws Exception {
        int databaseSizeBeforeUpdate = sdlRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSdlMockMvc.perform(put("/api/sdls")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sdl)))
            .andExpect(status().isBadRequest());

        // Validate the Sdl in the database
        List<Sdl> sdlList = sdlRepository.findAll();
        assertThat(sdlList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSdl() throws Exception {
        // Initialize the database
        sdlRepository.saveAndFlush(sdl);

        int databaseSizeBeforeDelete = sdlRepository.findAll().size();

        // Delete the sdl
        restSdlMockMvc.perform(delete("/api/sdls/{id}", sdl.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sdl> sdlList = sdlRepository.findAll();
        assertThat(sdlList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
