package com.smyw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("com.smyw.basemodule.web")
public class Swagger2 {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
               // .apis(RequestHandlerSelectors.basePackage("cn.zw.web"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("Spring Boot 整合 swagger ui")
                .description("Spring Boot 整合 swagger ui 和spring-data-JPA")
                .license("MIT")
                .licenseUrl("http://opensource.org/licenses/MIT")
                .contact(new Contact("宋文科", "http://www.5gchedao.com", "27654987@qq.com"))
                .version("1.0")
                .build();

        return apiInfo;
    }
}
