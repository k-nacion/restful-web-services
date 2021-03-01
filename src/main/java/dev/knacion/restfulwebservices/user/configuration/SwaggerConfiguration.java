package dev.knacion.restfulwebservices.user.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.DocumentationContextBuilder;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final Contact DEFAULT_CONTACT = new Contact("Kenn Nacion"
            , "twitter.com/knacion"
            , "nacionkenn@gmail.com");

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome Title API"
            , "Awesome Description for the Documentation"
            , "2.0"
            , "TOS"
            , DEFAULT_CONTACT
            , "Apache 2.0"
            , "http://www.apache.org/licenses/LICENSE-2.0"
            , new ArrayList<>()
    );

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>();
    static {
        DEFAULT_PRODUCES_AND_CONSUMES.add("application/json");
        DEFAULT_PRODUCES_AND_CONSUMES.add("application/xml");
    }

    //Bean - Docket
    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);

        return docket;
    }
    //swagger 2
    //all the paths
    //all the APIs

}
