package com.piter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket creatRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //指定扫描包下面的Swagger相关注解
                .apis(RequestHandlerSelectors.basePackage("com.piter"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("集成Swagger2")
                .description("构建RESTful APIs")
                .termsOfServiceUrl("http://www.piter.con/")
                .contact(new Contact("test", "com.piter", "123@qq.com"))
                .version("1.0.0")
                .build();
    }
}
