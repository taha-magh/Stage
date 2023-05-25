package ma.gov.marrakech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.gov.marrakech.web.rest.TestUtil;

public class PieceJointemodelJTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PieceJointemodelJ.class);
        PieceJointemodelJ pieceJointemodelJ1 = new PieceJointemodelJ();
        pieceJointemodelJ1.setId(1L);
        PieceJointemodelJ pieceJointemodelJ2 = new PieceJointemodelJ();
        pieceJointemodelJ2.setId(pieceJointemodelJ1.getId());
        assertThat(pieceJointemodelJ1).isEqualTo(pieceJointemodelJ2);
        pieceJointemodelJ2.setId(2L);
        assertThat(pieceJointemodelJ1).isNotEqualTo(pieceJointemodelJ2);
        pieceJointemodelJ1.setId(null);
        assertThat(pieceJointemodelJ1).isNotEqualTo(pieceJointemodelJ2);
    }
}
