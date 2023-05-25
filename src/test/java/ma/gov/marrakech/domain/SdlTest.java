package ma.gov.marrakech.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ma.gov.marrakech.web.rest.TestUtil;

public class SdlTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sdl.class);
        Sdl sdl1 = new Sdl();
        sdl1.setId(1L);
        Sdl sdl2 = new Sdl();
        sdl2.setId(sdl1.getId());
        assertThat(sdl1).isEqualTo(sdl2);
        sdl2.setId(2L);
        assertThat(sdl1).isNotEqualTo(sdl2);
        sdl1.setId(null);
        assertThat(sdl1).isNotEqualTo(sdl2);
    }
}
