package ma.gov.marrakech.service.dto;

//import static org.assertj.core.api.Assertions.assertThat;

import ma.gov.marrakech.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DelegataireDTOTest {

    @Test
    public void testEquals() {
        DelegataireDTO delegataireDTO1 = new DelegataireDTO();
        delegataireDTO1.setId(1L);

        DelegataireDTO delegataireDTO2 = new DelegataireDTO();
        delegataireDTO2.setId(2L);

        DelegataireDTO delegataireDTO3 = new DelegataireDTO();
        delegataireDTO3.setId(1L);

        // Reflexivity
        assertEquals(delegataireDTO1, delegataireDTO1);

        // Symmetry
        assertNotEquals(delegataireDTO1, delegataireDTO2);
        assertNotEquals(delegataireDTO2, delegataireDTO1);

        // Transitivity
        assertEquals(delegataireDTO1, delegataireDTO3);
        assertEquals(delegataireDTO3, delegataireDTO1);
        assertEquals(delegataireDTO1.hashCode(), delegataireDTO3.hashCode());

        // Inequality
        assertNotEquals(delegataireDTO1, null);
        assertNotEquals(delegataireDTO1, new Object());
    }
}

