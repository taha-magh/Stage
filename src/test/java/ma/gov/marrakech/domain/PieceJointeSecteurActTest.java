package ma.gov.marrakech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.gov.marrakech.web.rest.TestUtil;

public class PieceJointeSecteurActTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PieceJointeSecteurAct.class);
        PieceJointeSecteurAct pieceJointeSecteurAct1 = new PieceJointeSecteurAct();
        pieceJointeSecteurAct1.setId(1L);
        PieceJointeSecteurAct pieceJointeSecteurAct2 = new PieceJointeSecteurAct();
        pieceJointeSecteurAct2.setId(pieceJointeSecteurAct1.getId());
        assertThat(pieceJointeSecteurAct1).isEqualTo(pieceJointeSecteurAct2);
        pieceJointeSecteurAct2.setId(2L);
        assertThat(pieceJointeSecteurAct1).isNotEqualTo(pieceJointeSecteurAct2);
        pieceJointeSecteurAct1.setId(null);
        assertThat(pieceJointeSecteurAct1).isNotEqualTo(pieceJointeSecteurAct2);
    }
}
