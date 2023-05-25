package ma.gov.marrakech.web.rest;

import ma.gov.marrakech.MarrakechBackApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PieceJointeSecteurActResource} REST controller.
 */
@SpringBootTest(classes = MarrakechBackApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PieceJointeSecteurActResourceIT {

    private static final byte[] DEFAULT_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FILE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FILE_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    @Autowired
    private PieceJointeSecteurActRepository pieceJointeSecteurActRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPieceJointeSecteurActMockMvc;

    private PieceJointeSecteurAct pieceJointeSecteurAct;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PieceJointeSecteurAct createEntity(EntityManager em) {
        PieceJointeSecteurAct pieceJointeSecteurAct = new PieceJointeSecteurAct()
            .file(DEFAULT_FILE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE)
            .fileName(DEFAULT_FILE_NAME);
        return pieceJointeSecteurAct;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PieceJointeSecteurAct createUpdatedEntity(EntityManager em) {
        PieceJointeSecteurAct pieceJointeSecteurAct = new PieceJointeSecteurAct()
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileName(UPDATED_FILE_NAME);
        return pieceJointeSecteurAct;
    }

    @BeforeEach
    public void initTest() {
        pieceJointeSecteurAct = createEntity(em);
    }

    @Test
    @Transactional
    public void createPieceJointeSecteurAct() throws Exception {
        int databaseSizeBeforeCreate = pieceJointeSecteurActRepository.findAll().size();
        // Create the PieceJointeSecteurAct
        restPieceJointeSecteurActMockMvc.perform(post("/api/piece-jointe-secteur-acts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeSecteurAct)))
            .andExpect(status().isCreated());

        // Validate the PieceJointeSecteurAct in the database
        List<PieceJointeSecteurAct> pieceJointeSecteurActList = pieceJointeSecteurActRepository.findAll();
        assertThat(pieceJointeSecteurActList).hasSize(databaseSizeBeforeCreate + 1);
        PieceJointeSecteurAct testPieceJointeSecteurAct = pieceJointeSecteurActList.get(pieceJointeSecteurActList.size() - 1);
        assertThat(testPieceJointeSecteurAct.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testPieceJointeSecteurAct.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
        assertThat(testPieceJointeSecteurAct.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
        assertThat(testPieceJointeSecteurAct.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
    }

    @Test
    @Transactional
    public void createPieceJointeSecteurActWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pieceJointeSecteurActRepository.findAll().size();

        // Create the PieceJointeSecteurAct with an existing ID
        pieceJointeSecteurAct.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPieceJointeSecteurActMockMvc.perform(post("/api/piece-jointe-secteur-acts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeSecteurAct)))
            .andExpect(status().isBadRequest());

        // Validate the PieceJointeSecteurAct in the database
        List<PieceJointeSecteurAct> pieceJointeSecteurActList = pieceJointeSecteurActRepository.findAll();
        assertThat(pieceJointeSecteurActList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFileContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pieceJointeSecteurActRepository.findAll().size();
        // set the field null
        pieceJointeSecteurAct.setFileContentType(null);

        // Create the PieceJointeSecteurAct, which fails.


        restPieceJointeSecteurActMockMvc.perform(post("/api/piece-jointe-secteur-acts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeSecteurAct)))
            .andExpect(status().isBadRequest());

        List<PieceJointeSecteurAct> pieceJointeSecteurActList = pieceJointeSecteurActRepository.findAll();
        assertThat(pieceJointeSecteurActList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFileNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pieceJointeSecteurActRepository.findAll().size();
        // set the field null
        pieceJointeSecteurAct.setFileName(null);

        // Create the PieceJointeSecteurAct, which fails.


        restPieceJointeSecteurActMockMvc.perform(post("/api/piece-jointe-secteur-acts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeSecteurAct)))
            .andExpect(status().isBadRequest());

        List<PieceJointeSecteurAct> pieceJointeSecteurActList = pieceJointeSecteurActRepository.findAll();
        assertThat(pieceJointeSecteurActList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPieceJointeSecteurActs() throws Exception {
        // Initialize the database
        pieceJointeSecteurActRepository.saveAndFlush(pieceJointeSecteurAct);

        // Get all the pieceJointeSecteurActList
        restPieceJointeSecteurActMockMvc.perform(get("/api/piece-jointe-secteur-acts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pieceJointeSecteurAct.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(Base64Utils.encodeToString(DEFAULT_FILE))))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)));
    }

    @Test
    @Transactional
    public void getPieceJointeSecteurAct() throws Exception {
        // Initialize the database
        pieceJointeSecteurActRepository.saveAndFlush(pieceJointeSecteurAct);

        // Get the pieceJointeSecteurAct
        restPieceJointeSecteurActMockMvc.perform(get("/api/piece-jointe-secteur-acts/{id}", pieceJointeSecteurAct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pieceJointeSecteurAct.getId().intValue()))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.file").value(Base64Utils.encodeToString(DEFAULT_FILE)))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingPieceJointeSecteurAct() throws Exception {
        // Get the pieceJointeSecteurAct
        restPieceJointeSecteurActMockMvc.perform(get("/api/piece-jointe-secteur-acts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePieceJointeSecteurAct() throws Exception {
        // Initialize the database
        pieceJointeSecteurActRepository.saveAndFlush(pieceJointeSecteurAct);

        int databaseSizeBeforeUpdate = pieceJointeSecteurActRepository.findAll().size();

        // Update the pieceJointeSecteurAct
        PieceJointeSecteurAct updatedPieceJointeSecteurAct = pieceJointeSecteurActRepository.findById(pieceJointeSecteurAct.getId()).get();
        // Disconnect from session so that the updates on updatedPieceJointeSecteurAct are not directly saved in db
        em.detach(updatedPieceJointeSecteurAct);
        updatedPieceJointeSecteurAct
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileName(UPDATED_FILE_NAME);

        restPieceJointeSecteurActMockMvc.perform(put("/api/piece-jointe-secteur-acts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPieceJointeSecteurAct)))
            .andExpect(status().isOk());

        // Validate the PieceJointeSecteurAct in the database
        List<PieceJointeSecteurAct> pieceJointeSecteurActList = pieceJointeSecteurActRepository.findAll();
        assertThat(pieceJointeSecteurActList).hasSize(databaseSizeBeforeUpdate);
        PieceJointeSecteurAct testPieceJointeSecteurAct = pieceJointeSecteurActList.get(pieceJointeSecteurActList.size() - 1);
        assertThat(testPieceJointeSecteurAct.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testPieceJointeSecteurAct.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
        assertThat(testPieceJointeSecteurAct.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
        assertThat(testPieceJointeSecteurAct.getFileName()).isEqualTo(UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingPieceJointeSecteurAct() throws Exception {
        int databaseSizeBeforeUpdate = pieceJointeSecteurActRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPieceJointeSecteurActMockMvc.perform(put("/api/piece-jointe-secteur-acts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeSecteurAct)))
            .andExpect(status().isBadRequest());

        // Validate the PieceJointeSecteurAct in the database
        List<PieceJointeSecteurAct> pieceJointeSecteurActList = pieceJointeSecteurActRepository.findAll();
        assertThat(pieceJointeSecteurActList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePieceJointeSecteurAct() throws Exception {
        // Initialize the database
        pieceJointeSecteurActRepository.saveAndFlush(pieceJointeSecteurAct);

        int databaseSizeBeforeDelete = pieceJointeSecteurActRepository.findAll().size();

        // Delete the pieceJointeSecteurAct
        restPieceJointeSecteurActMockMvc.perform(delete("/api/piece-jointe-secteur-acts/{id}", pieceJointeSecteurAct.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PieceJointeSecteurAct> pieceJointeSecteurActList = pieceJointeSecteurActRepository.findAll();
        assertThat(pieceJointeSecteurActList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
