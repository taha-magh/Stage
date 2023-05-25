package ma.gov.marrakech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket apisForAutoEcole() {
        String groupName = "marrakech";
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(("ma.gov.marrakech")))
            .paths(PathSelectors.any())
            .build()
            .groupName(groupName)
            .apiInfo(metadataAbb());
    }

    private ApiInfo metadataAbb() {
        return new ApiInfoBuilder()
            .title("marrakech")
            .description("Swagger for marrakech")
            .version("1.0")
            .build();
    }
}
