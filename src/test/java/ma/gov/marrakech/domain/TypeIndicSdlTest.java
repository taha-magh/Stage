package ma.gov.marrakech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.gov.marrakech.web.rest.TestUtil;

public class TypeIndicSdlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeIndicSdl.class);
        TypeIndicSdl typeIndicSdl1 = new TypeIndicSdl();
        typeIndicSdl1.setId(1L);
        TypeIndicSdl typeIndicSdl2 = new TypeIndicSdl();
        typeIndicSdl2.setId(typeIndicSdl1.getId());
        assertThat(typeIndicSdl1).isEqualTo(typeIndicSdl2);
        typeIndicSdl2.setId(2L);
        assertThat(typeIndicSdl1).isNotEqualTo(typeIndicSdl2);
        typeIndicSdl1.setId(null);
        assertThat(typeIndicSdl1).isNotEqualTo(typeIndicSdl2);
    }
}
