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
 * Integration tests for the {@link PieceJointeStatutResource} REST controller.
 */
@SpringBootTest(classes = MarrakechBackApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PieceJointeStatutResourceIT {

    private static final byte[] DEFAULT_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FILE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FILE_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    @Autowired
    private PieceJointeStatutRepository pieceJointeStatutRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPieceJointeStatutMockMvc;

    private PieceJointeStatut pieceJointeStatut;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PieceJointeStatut createEntity(EntityManager em) {
        PieceJointeStatut pieceJointeStatut = new PieceJointeStatut()
            .file(DEFAULT_FILE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE)
            .fileName(DEFAULT_FILE_NAME);
        return pieceJointeStatut;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PieceJointeStatut createUpdatedEntity(EntityManager em) {
        PieceJointeStatut pieceJointeStatut = new PieceJointeStatut()
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileName(UPDATED_FILE_NAME);
        return pieceJointeStatut;
    }

    @BeforeEach
    public void initTest() {
        pieceJointeStatut = createEntity(em);
    }

    @Test
    @Transactional
    public void createPieceJointeStatut() throws Exception {
        int databaseSizeBeforeCreate = pieceJointeStatutRepository.findAll().size();
        // Create the PieceJointeStatut
        restPieceJointeStatutMockMvc.perform(post("/api/piece-jointe-statuts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeStatut)))
            .andExpect(status().isCreated());

        // Validate the PieceJointeStatut in the database
        List<PieceJointeStatut> pieceJointeStatutList = pieceJointeStatutRepository.findAll();
        assertThat(pieceJointeStatutList).hasSize(databaseSizeBeforeCreate + 1);
        PieceJointeStatut testPieceJointeStatut = pieceJointeStatutList.get(pieceJointeStatutList.size() - 1);
        assertThat(testPieceJointeStatut.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testPieceJointeStatut.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
        assertThat(testPieceJointeStatut.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
        assertThat(testPieceJointeStatut.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
    }

    @Test
    @Transactional
    public void createPieceJointeStatutWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pieceJointeStatutRepository.findAll().size();

        // Create the PieceJointeStatut with an existing ID
        pieceJointeStatut.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPieceJointeStatutMockMvc.perform(post("/api/piece-jointe-statuts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeStatut)))
            .andExpect(status().isBadRequest());

        // Validate the PieceJointeStatut in the database
        List<PieceJointeStatut> pieceJointeStatutList = pieceJointeStatutRepository.findAll();
        assertThat(pieceJointeStatutList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFileContentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pieceJointeStatutRepository.findAll().size();
        // set the field null
        pieceJointeStatut.setFileContentType(null);

        // Create the PieceJointeStatut, which fails.


        restPieceJointeStatutMockMvc.perform(post("/api/piece-jointe-statuts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeStatut)))
            .andExpect(status().isBadRequest());

        List<PieceJointeStatut> pieceJointeStatutList = pieceJointeStatutRepository.findAll();
        assertThat(pieceJointeStatutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFileNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pieceJointeStatutRepository.findAll().size();
        // set the field null
        pieceJointeStatut.setFileName(null);

        // Create the PieceJointeStatut, which fails.


        restPieceJointeStatutMockMvc.perform(post("/api/piece-jointe-statuts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeStatut)))
            .andExpect(status().isBadRequest());

        List<PieceJointeStatut> pieceJointeStatutList = pieceJointeStatutRepository.findAll();
        assertThat(pieceJointeStatutList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPieceJointeStatuts() throws Exception {
        // Initialize the database
        pieceJointeStatutRepository.saveAndFlush(pieceJointeStatut);

        // Get all the pieceJointeStatutList
        restPieceJointeStatutMockMvc.perform(get("/api/piece-jointe-statuts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pieceJointeStatut.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(Base64Utils.encodeToString(DEFAULT_FILE))))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)));
    }

    @Test
    @Transactional
    public void getPieceJointeStatut() throws Exception {
        // Initialize the database
        pieceJointeStatutRepository.saveAndFlush(pieceJointeStatut);

        // Get the pieceJointeStatut
        restPieceJointeStatutMockMvc.perform(get("/api/piece-jointe-statuts/{id}", pieceJointeStatut.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pieceJointeStatut.getId().intValue()))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.file").value(Base64Utils.encodeToString(DEFAULT_FILE)))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingPieceJointeStatut() throws Exception {
        // Get the pieceJointeStatut
        restPieceJointeStatutMockMvc.perform(get("/api/piece-jointe-statuts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePieceJointeStatut() throws Exception {
        // Initialize the database
        pieceJointeStatutRepository.saveAndFlush(pieceJointeStatut);

        int databaseSizeBeforeUpdate = pieceJointeStatutRepository.findAll().size();

        // Update the pieceJointeStatut
        PieceJointeStatut updatedPieceJointeStatut = pieceJointeStatutRepository.findById(pieceJointeStatut.getId()).get();
        // Disconnect from session so that the updates on updatedPieceJointeStatut are not directly saved in db
        em.detach(updatedPieceJointeStatut);
        updatedPieceJointeStatut
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE)
            .fileName(UPDATED_FILE_NAME);

        restPieceJointeStatutMockMvc.perform(put("/api/piece-jointe-statuts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPieceJointeStatut)))
            .andExpect(status().isOk());

        // Validate the PieceJointeStatut in the database
        List<PieceJointeStatut> pieceJointeStatutList = pieceJointeStatutRepository.findAll();
        assertThat(pieceJointeStatutList).hasSize(databaseSizeBeforeUpdate);
        PieceJointeStatut testPieceJointeStatut = pieceJointeStatutList.get(pieceJointeStatutList.size() - 1);
        assertThat(testPieceJointeStatut.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testPieceJointeStatut.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
        assertThat(testPieceJointeStatut.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
        assertThat(testPieceJointeStatut.getFileName()).isEqualTo(UPDATED_FILE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingPieceJointeStatut() throws Exception {
        int databaseSizeBeforeUpdate = pieceJointeStatutRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPieceJointeStatutMockMvc.perform(put("/api/piece-jointe-statuts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pieceJointeStatut)))
            .andExpect(status().isBadRequest());

        // Validate the PieceJointeStatut in the database
        List<PieceJointeStatut> pieceJointeStatutList = pieceJointeStatutRepository.findAll();
        assertThat(pieceJointeStatutList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePieceJointeStatut() throws Exception {
        // Initialize the database
        pieceJointeStatutRepository.saveAndFlush(pieceJointeStatut);

        int databaseSizeBeforeDelete = pieceJointeStatutRepository.findAll().size();

        // Delete the pieceJointeStatut
        restPieceJointeStatutMockMvc.perform(delete("/api/piece-jointe-statuts/{id}", pieceJointeStatut.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PieceJointeStatut> pieceJointeStatutList = pieceJointeStatutRepository.findAll();
        assertThat(pieceJointeStatutList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
