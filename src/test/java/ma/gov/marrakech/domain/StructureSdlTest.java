package ma.gov.marrakech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.gov.marrakech.web.rest.TestUtil;

public class StructureSdlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StructureSdl.class);
        StructureSdl structureSdl1 = new StructureSdl();
        structureSdl1.setId(1L);
        StructureSdl structureSdl2 = new StructureSdl();
        structureSdl2.setId(structureSdl1.getId());
        assertThat(structureSdl1).isEqualTo(structureSdl2);
        structureSdl2.setId(2L);
        assertThat(structureSdl1).isNotEqualTo(structureSdl2);
        structureSdl1.setId(null);
        assertThat(structureSdl1).isNotEqualTo(structureSdl2);
    }
}
