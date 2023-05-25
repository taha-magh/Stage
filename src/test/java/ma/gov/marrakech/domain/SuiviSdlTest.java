package ma.gov.marrakech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.gov.marrakech.web.rest.TestUtil;

public class SuiviSdlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuiviSdl.class);
        SuiviSdl suiviSdl1 = new SuiviSdl();
        suiviSdl1.setId(1L);
        SuiviSdl suiviSdl2 = new SuiviSdl();
        suiviSdl2.setId(suiviSdl1.getId());
        assertThat(suiviSdl1).isEqualTo(suiviSdl2);
        suiviSdl2.setId(2L);
        assertThat(suiviSdl1).isNotEqualTo(suiviSdl2);
        suiviSdl1.setId(null);
        assertThat(suiviSdl1).isNotEqualTo(suiviSdl2);
    }
}
