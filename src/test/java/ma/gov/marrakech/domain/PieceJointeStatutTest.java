package ma.gov.marrakech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.gov.marrakech.web.rest.TestUtil;

public class PieceJointeStatutTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PieceJointeStatut.class);
        PieceJointeStatut pieceJointeStatut1 = new PieceJointeStatut();
        pieceJointeStatut1.setId(1L);
        PieceJointeStatut pieceJointeStatut2 = new PieceJointeStatut();
        pieceJointeStatut2.setId(pieceJointeStatut1.getId());
        assertThat(pieceJointeStatut1).isEqualTo(pieceJointeStatut2);
        pieceJointeStatut2.setId(2L);
        assertThat(pieceJointeStatut1).isNotEqualTo(pieceJointeStatut2);
        pieceJointeStatut1.setId(null);
        assertThat(pieceJointeStatut1).isNotEqualTo(pieceJointeStatut2);
    }
}
