package com.hgt.demo.config;

import io.swagger.annotations.Api;
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
 * @Title : SwaggerConfig
 * @Description： API接口配置
 * @Author ：liuqiang
 * @Date ：Created in 2019/12/18 13:46
 * @Modified By：
 * @Version: 1.0$
 * Copyright: Copyright (c) 2019
 * Company: 恒歌科技
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("测试接口文档")
                .description("测试接口文档")
                .contact(new Contact("liuqiang", "", "liuqiang@henggetec.com"))
                .version("1.0.0")
                .build();
    }
}
