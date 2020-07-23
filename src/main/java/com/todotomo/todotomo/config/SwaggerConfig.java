package com.todotomo.todotomo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket taskApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/tasks/**"))
                .build()
                .securitySchemes(Arrays.asList(apiKey()))
                .groupName("tasks api")
                .apiInfo(ApiInfo())
                .useDefaultResponseMessages(false);
    }
    @Bean
    public Docket usersApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/users/**"))
                .build()
                .groupName("users api")
                .apiInfo(ApiInfo())
                .useDefaultResponseMessages(false);
    }

    @Bean
    public Docket sessionApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/session/**"))
                .build()
                .groupName("session api")
                .apiInfo(ApiInfo())
                .useDefaultResponseMessages(false);
    }


    private ApiInfo ApiInfo() {
        return new ApiInfoBuilder()
                .title("TODOs for TOMOrrow")
                .description("simple todo list apis")
                .contact(new Contact("dijkstra", "https://example.com/", "dijkstra@gmail.com"))
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer +accessToken", "Authorization", "header");
    }



}
