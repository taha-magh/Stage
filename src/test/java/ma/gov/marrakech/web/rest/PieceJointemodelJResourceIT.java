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
 * Integration tests for the {@link PieceJointemodelJResource} REST controller.
 */
@SpringBootTest(classes = MarrakechBackApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PieceJointemodelJResourceIT {

    private static final byte[] DEFAULT_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FILE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FILE_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    @Autowired
    private PieceJointemodelJRepository pieceJointemodelJRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPieceJointemodelJMockMvc;

    private PieceJointemodelJ pieceJointemodelJ;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PieceJointemodelJ createEntity(EntityManager em) {
        PieceJointemodelJ pieceJointemodelJ = new PieceJointemodelJ()
            .file(DEFAULT_FILE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE)
            .fileName(DEFAULT_FILE_NAME);
        return pieceJointemodelJ;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PieceJointemodelJ createUpdatedEntity(EntityManager em) {
        PieceJointemodelJ pieceJointemodelJ = new PieceJointemodelJ()
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileName(UPDATED_FILE_NAME);
        return pieceJointemodelJ;
    }

    @BeforeEach
    public void initTest() {
        pieceJointemodelJ = createEntity(em);
    }

    @Test
    @Transactional
    public void createPieceJointemodelJ() throws Exception {
        int databaseSizeBeforeCreate = pieceJointemodelJRepository.findAll().size();
        // Create the PieceJointemodelJ
        restPieceJointemodelJMockMvc.perform(post("/api/piece-jointemodel-js")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointemodelJ)))
            .andExpect(status().isCreated());

        // Validate the PieceJointemodelJ in the database
        List<PieceJointemodelJ> pieceJointemodelJList = pieceJointemodelJRepository.findAll();
        assertThat(pieceJointemodelJList).hasSize(databaseSizeBeforeCreate + 1);
        PieceJointemodelJ testPieceJointemodelJ = pieceJointemodelJList.get(pieceJointemodelJList.size() - 1);
        assertThat(testPieceJointemodelJ.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testPieceJointemodelJ.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
        assertThat(testPieceJointemodelJ.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
        assertThat(testPieceJointemodelJ.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
    }

    @Test
    @Transactional
    public void createPieceJointemodelJWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pieceJointemodelJRepository.findAll().size();

        // Create the PieceJointemodelJ with an existing ID
        pieceJointemodelJ.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPieceJointemodelJMockMvc.perform(post("/api/piece-jointemodel-js")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointemodelJ)))
            .andExpect(status().isBadRequest());

        // Validate the PieceJointemodelJ in the database
        List<PieceJointemodelJ> pieceJointemodelJList = pieceJointemodelJRepository.findAll();
        assertThat(pieceJointemodelJList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFileContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pieceJointemodelJRepository.findAll().size();
        // set the field null
        pieceJointemodelJ.setFileContentType(null);

        // Create the PieceJointemodelJ, which fails.


        restPieceJointemodelJMockMvc.perform(post("/api/piece-jointemodel-js")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointemodelJ)))
            .andExpect(status().isBadRequest());

        List<PieceJointemodelJ> pieceJointemodelJList = pieceJointemodelJRepository.findAll();
        assertThat(pieceJointemodelJList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFileNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pieceJointemodelJRepository.findAll().size();
        // set the field null
        pieceJointemodelJ.setFileName(null);

        // Create the PieceJointemodelJ, which fails.


        restPieceJointemodelJMockMvc.perform(post("/api/piece-jointemodel-js")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointemodelJ)))
            .andExpect(status().isBadRequest());

        List<PieceJointemodelJ> pieceJointemodelJList = pieceJointemodelJRepository.findAll();
        assertThat(pieceJointemodelJList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPieceJointemodelJS() throws Exception {
        // Initialize the database
        pieceJointemodelJRepository.saveAndFlush(pieceJointemodelJ);

        // Get all the pieceJointemodelJList
        restPieceJointemodelJMockMvc.perform(get("/api/piece-jointemodel-js?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pieceJointemodelJ.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(Base64Utils.encodeToString(DEFAULT_FILE))))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)));
    }

    @Test
    @Transactional
    public void getPieceJointemodelJ() throws Exception {
        // Initialize the database
        pieceJointemodelJRepository.saveAndFlush(pieceJointemodelJ);

        // Get the pieceJointemodelJ
        restPieceJointemodelJMockMvc.perform(get("/api/piece-jointemodel-js/{id}", pieceJointemodelJ.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pieceJointemodelJ.getId().intValue()))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.file").value(Base64Utils.encodeToString(DEFAULT_FILE)))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingPieceJointemodelJ() throws Exception {
        // Get the pieceJointemodelJ
        restPieceJointemodelJMockMvc.perform(get("/api/piece-jointemodel-js/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePieceJointemodelJ() throws Exception {
        // Initialize the database
        pieceJointemodelJRepository.saveAndFlush(pieceJointemodelJ);

        int databaseSizeBeforeUpdate = pieceJointemodelJRepository.findAll().size();

        // Update the pieceJointemodelJ
        PieceJointemodelJ updatedPieceJointemodelJ = pieceJointemodelJRepository.findById(pieceJointemodelJ.getId()).get();
        // Disconnect from session so that the updates on updatedPieceJointemodelJ are not directly saved in db
        em.detach(updatedPieceJointemodelJ);
        updatedPieceJointemodelJ
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileName(UPDATED_FILE_NAME);

        restPieceJointemodelJMockMvc.perform(put("/api/piece-jointemodel-js")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPieceJointemodelJ)))
            .andExpect(status().isOk());

        // Validate the PieceJointemodelJ in the database
        List<PieceJointemodelJ> pieceJointemodelJList = pieceJointemodelJRepository.findAll();
        assertThat(pieceJointemodelJList).hasSize(databaseSizeBeforeUpdate);
        PieceJointemodelJ testPieceJointemodelJ = pieceJointemodelJList.get(pieceJointemodelJList.size() - 1);
        assertThat(testPieceJointemodelJ.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testPieceJointemodelJ.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
        assertThat(testPieceJointemodelJ.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
        assertThat(testPieceJointemodelJ.getFileName()).isEqualTo(UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingPieceJointemodelJ() throws Exception {
        int databaseSizeBeforeUpdate = pieceJointemodelJRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPieceJointemodelJMockMvc.perform(put("/api/piece-jointemodel-js")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointemodelJ)))
            .andExpect(status().isBadRequest());

        // Validate the PieceJointemodelJ in the database
        List<PieceJointemodelJ> pieceJointemodelJList = pieceJointemodelJRepository.findAll();
        assertThat(pieceJointemodelJList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePieceJointemodelJ() throws Exception {
        // Initialize the database
        pieceJointemodelJRepository.saveAndFlush(pieceJointemodelJ);

        int databaseSizeBeforeDelete = pieceJointemodelJRepository.findAll().size();

        // Delete the pieceJointemodelJ
        restPieceJointemodelJMockMvc.perform(delete("/api/piece-jointemodel-js/{id}", pieceJointemodelJ.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PieceJointemodelJ> pieceJointemodelJList = pieceJointemodelJRepository.findAll();
        assertThat(pieceJointemodelJList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
