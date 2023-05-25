package ma.gov.marrakech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.gov.marrakech.web.rest.TestUtil;

public class SuiviDelegataireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuiviDelegataire.class);
        SuiviDelegataire suiviDelegataire1 = new SuiviDelegataire();
        suiviDelegataire1.setId(1L);
        SuiviDelegataire suiviDelegataire2 = new SuiviDelegataire();
        suiviDelegataire2.setId(suiviDelegataire1.getId());
        assertThat(suiviDelegataire1).isEqualTo(suiviDelegataire2);
        suiviDelegataire2.setId(2L);
        assertThat(suiviDelegataire1).isNotEqualTo(suiviDelegataire2);
        suiviDelegataire1.setId(null);
        assertThat(suiviDelegataire1).isNotEqualTo(suiviDelegataire2);
    }
}
