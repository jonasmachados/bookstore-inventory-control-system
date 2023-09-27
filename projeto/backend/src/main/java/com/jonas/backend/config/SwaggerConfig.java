package com.jonas.backend.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public Docket api() {
        
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jonas.backend.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(builApiInfo());
        
    }
    
    private ApiInfo builApiInfo(){
        
        return new ApiInfoBuilder()
                .title("API Livro")
                .description("REST API Livro para gerenciamento de livraria")
                .version("1.0.0")
                .contact(new Contact(
                        "Jonas Machado",
                        "github/jonasmachados",
                        null))
                .build();
        
    }

}
