package ma.gov.marrakech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.gov.marrakech.web.rest.TestUtil;

public class StructureDelegataireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StructureDelegataire.class);
        StructureDelegataire structureDelegataire1 = new StructureDelegataire();
        structureDelegataire1.setId(1L);
        StructureDelegataire structureDelegataire2 = new StructureDelegataire();
        structureDelegataire2.setId(structureDelegataire1.getId());
        assertThat(structureDelegataire1).isEqualTo(structureDelegataire2);
        structureDelegataire2.setId(2L);
        assertThat(structureDelegataire1).isNotEqualTo(structureDelegataire2);
        structureDelegataire1.setId(null);
        assertThat(structureDelegataire1).isNotEqualTo(structureDelegataire2);
    }
}
