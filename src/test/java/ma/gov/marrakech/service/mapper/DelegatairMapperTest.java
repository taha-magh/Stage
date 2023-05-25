package ma.gov.marrakech.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;

class DelegatairMapperTest {

  private DelegatairMapper delegatairMapper;

  @BeforeEach
  public void setUp() {
    delegatairMapper = new DelegatairMapperImpl();
  }
}
