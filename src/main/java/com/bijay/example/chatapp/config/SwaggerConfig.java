package com.bijay.example.chatapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Collections;

/**
 * @Author Bijay Thapa
 * @Project chatapp
 * @created 4/1/21 - 9:29 PM
 */

@Configuration
@EnableSwagger2
//@EnableWebMvc
//public class UmWebConfig extends WebMvcConfigurerAdapter {
public class SwaggerConfig {


    @Bean
    public Docket mainConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .paths(PathSelectors.any())
//                .apis(RequestHandlerSelectors.any())
//                .build()
//                .pathMapping("/chatServer/")
                .paths(PathSelectors.ant("/chatServer/**"))
                .apis(RequestHandlerSelectors.basePackage("com.bijay.example.chatapp"))
                .build()
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(apiInfoDetails());
    }

    private ApiInfo apiInfoDetails() {
        return new ApiInfo(
                "Messaging API",
                "RESTful API for chatting among end-users in the system",
                "1.0",
                "Free to Use",
                new springfox.documentation.service.Contact("Bijay Thapa", "https://bijaythapa.com.np",
                        "info@bijaythapa.com.np"),
                "API License",
                "https://bijaythapa.com.np",
                Collections.emptyList());
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META_INF/resources/webjars");
//    }
}
