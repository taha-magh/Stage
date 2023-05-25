package ma.gov.marrakech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.gov.marrakech.web.rest.TestUtil;

public class TypeIndicDelegataireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeIndicDelegataire.class);
        TypeIndicDelegataire typeIndicDelegataire1 = new TypeIndicDelegataire();
        typeIndicDelegataire1.setId(1L);
        TypeIndicDelegataire typeIndicDelegataire2 = new TypeIndicDelegataire();
        typeIndicDelegataire2.setId(typeIndicDelegataire1.getId());
        assertThat(typeIndicDelegataire1).isEqualTo(typeIndicDelegataire2);
        typeIndicDelegataire2.setId(2L);
        assertThat(typeIndicDelegataire1).isNotEqualTo(typeIndicDelegataire2);
        typeIndicDelegataire1.setId(null);
        assertThat(typeIndicDelegataire1).isNotEqualTo(typeIndicDelegataire2);
    }
}
