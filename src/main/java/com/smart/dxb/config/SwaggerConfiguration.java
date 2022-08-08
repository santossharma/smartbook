package com.smart.dxb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Created by santoshsharma on 07 Aug, 2022
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket docketBean() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.smart.dxb"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Smart Book API",
                "Smart Book API for Smart Dubai Test",
                "1.0", "urn:tos",
                new Contact("DXB-SANT", "https://github.com/santossharma",
                        "santosh_k_sh@live.com"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
            Collections.emptyList());
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
            .defaultModelsExpandDepth(-1)
            .build();
    }

}
