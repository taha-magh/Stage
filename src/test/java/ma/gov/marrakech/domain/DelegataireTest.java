package ma.gov.marrakech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.gov.marrakech.web.rest.TestUtil;

public class DelegataireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Delegataire.class);
        Delegataire delegataire1 = new Delegataire();
        delegataire1.setId(1L);
        Delegataire delegataire2 = new Delegataire();
        delegataire2.setId(delegataire1.getId());
        assertThat(delegataire1).isEqualTo(delegataire2);
        delegataire2.setId(2L);
        assertThat(delegataire1).isNotEqualTo(delegataire2);
        delegataire1.setId(null);
        assertThat(delegataire1).isNotEqualTo(delegataire2);
    }
}
